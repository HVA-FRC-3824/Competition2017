package org.usfirst.frc3824.Competition2017.utils;

import java.util.Iterator;
import java.util.List;

import org.usfirst.frc3824.Competition2017.Constants;

public class TargetingInfo {
	public int    offsetFromTargetX;	// pixels from targeting point in the X direction
	public int    offsetFromTargetY;	// pixels from targeting point in the Y direction
	public double distanceToTarget;		// robot's distance to the target
	public double bottomLineLength;		// length of the bottom line of target - relative to horizontal
	public double bottomLineAngle;		// angle of the bottom line of target - relative to horizontal

	public boolean valid; // is targeting info valid
	
	public int    centerX;
	public int    centerY;
	public int    height;
	public int    width;
	public int    area;
	public double rotationAngleOffset;	// robot's rotation offset from perfectly lined up with target
	public double positionAngleOffset;	// robot's position offset on field from the centerline of the target
	
	/**
	 * Method to return the distance from the largest target based on the target area
	 */
	public double getDistanceFromLargestTarget()
	{
		// y = 9E-06x2 - 0.1589x + 583.49
		return (9e-6 * area * area) - (0.1589 * area) + 583.49;
	}

	private TargetingInfo() { }
	
	/**
	 * Static method to calculate targeting info from a list of lines and targets
	 * @param lines
	 * @param targets
	 * @param imgWidth
	 */
	public static TargetingInfo calculateTargetingInfo(List<Line> lines, List<Target> targets, int imgWidth)
	{		
		TargetingInfo targetInfo = new TargetingInfo();
		
		// if there are no targets or no lines just exit
		if (targets.size() == 0 || lines.size() == 0) return null;
		
		// ***************************************************************************************
		// Calculate largest target data
		targets.sort(Target.AreaComparatorDescending);	// sort from largest to smallest
		Target largestTarget      = targets.get(0);		            // get the largest target
		targetInfo.centerX = largestTarget.centerX;             // copy it into the TargetInfo object
		targetInfo.centerY = largestTarget.centerY;
		targetInfo.height  = largestTarget.height;
		targetInfo.width   = largestTarget.width;
		targetInfo.area    = largestTarget.area;

		// ***************************************************************************************
		// Calculate line information
		
		Iterator<Line> lineIterator = lines.iterator();
		while (lineIterator.hasNext())
		{
			Line line = lineIterator.next();
			
			// remove "vertical" lines
			double angle = Math.abs(line.angle);
			if ((angle >= 45.0) && (angle <= 135.0))
			{
				lineIterator.remove();
			}

			// remove any horizontal lines whose X midpoint is more than 10 pixels from the
			// largest target center
			else if (Math.abs(largestTarget.centerX - line.midpointX()) > 10)
			{
				lineIterator.remove();
			}


		}
		
		// There should be exactly two lines left, but lets verify and not assume
		if (lines.size() > 2) return null; //TODO: log some sort of message about why target calculation failed
		
		lines.sort(Line.LineXComparator);
		Line targetLine = lines.get(0);
		
		targetInfo.bottomLineAngle  = targetLine.angle;
		targetInfo.bottomLineLength = targetLine.length;

		// --------------------------------------------
		// calculate robot's distance from the tower based on
		// line size and angle
		// ---
		targetInfo.distanceToTarget = calculateDistanceToTargetWithLine(targetLine.length);

		// --------------------------------------------
		// calculate rotation offset of robot
		// ---
		// calculate offset from "OnTarget" in pixels
		targetInfo.offsetFromTargetX = targetingPositionX(largestTarget, targetInfo.distanceToTarget, targetInfo.bottomLineAngle) - largestTarget.centerX;
		targetInfo.offsetFromTargetY = targetingPositionY(largestTarget, targetInfo.distanceToTarget, targetInfo.bottomLineAngle) - largestTarget.centerY;

		// convert the offset in pixels to a normalized range where -1 is one half an
		// image width to the left and 1 is one half an image width to the right.
		// if the image is positioned to the right, the robot is too far left.
		// so this return value is flipped
		double positionFromOnTargetXNormalized = (-targetInfo.offsetFromTargetX) / (imgWidth / 2.0);

		targetInfo.rotationAngleOffset = positionFromOnTargetXNormalized * (Constants.CAM_FOV / 2.0);

		// --------------------------------------------
		// calculate robot's offset from centerline of target
		// ---
		targetInfo.positionAngleOffset = calculatePositionAngleOffsetWithLine(targetLine.angle);

		// Return the targeting information
		return targetInfo;
	}
		
	/**
	 * Method to return the desired target X position based on the distance and
	 * angle to the target
	 */
	static int targetingPositionX(Target foundTarget, double distanceToTarget, double lineAngle)
	{
		// Get the image X target position
		int targetX = Constants.IMAGE_ON_TARGET_X_POSITION;
		
		// Calculate the X pixel offset
		// -15 -> -2
		// -10 -> -1
		//  -5 ->  0
		//   0 ->  0
		//   5 ->  0
		//  10 ->  1
		//  15 ->  2 
		if (Math.abs(lineAngle) > 90.0)
		{
			if (lineAngle > 0)
				lineAngle -= 180.0;
			else
				lineAngle += 180.0;
		}
		
		int Xoffset = (int) (lineAngle / 2.5);

		// Modify the offset based on the angle
		targetX -= Xoffset;
		
		return targetX;
	}

	/**
	 * Method to return the desired targetY position based on the distance and
	 * angle to the target
	 */
	static int targetingPositionY(Target foundTarget, double distanceToTarget, double lineAngle)
	{
		// Determine the image target Y pixel
		// y = Ax^2 + Bx + C
		int targetY = (int) ((Constants.IMAGE_Y_A * distanceToTarget * distanceToTarget) + 
		                     (Constants.IMAGE_Y_B * distanceToTarget) +
		                      Constants.IMAGE_Y_C);

		// Calculate the Y pixel offset
		// -15 -> 2
		// -10 -> 1
		//  -5 -> 0
		//   0 -> 0
		//   5 -> 0
		//  10 -> 1
		//  15 -> 2  
		if (Math.abs(lineAngle) > 90.0)
		{
			if (lineAngle > 0)
				lineAngle -= 180.0;
			else
				lineAngle += 180.0;
		}
		
		int Yoffset = (int) (Math.abs(lineAngle) / 5.0);

		// Modify the offset based on the angle
		targetY += Yoffset;
		
		// Additional offset to compensate for X position
		targetY += Constants.IMAGE_ON_TARGET_Y_OFFSET;
		
		// Return the Y target pixel
		return targetY;
	}
	
	/**
	 * Method to return the position offset on the field from center of a target based on
	 * the angle of the horizontal line
	 * 
	 * Use line fit:
	 *   line angle versus actual angle
	 */
	public static double calculatePositionAngleOffsetWithLine(double lineAngle)
	{
		double angleOffset;

		if (lineAngle < 75)
		{
			angleOffset = 0 - lineAngle; // left of center is negative angle
		}
		else
		{
			angleOffset = 0 - (lineAngle - 360.0); // right of center is positive angle
		}

		return angleOffset;
	}
	
	/**
	 * Method to return the distance from the specified target based on the target line length
	 * and angle of the line
	 * 
	 * Use line fit:
	 * 	line length versus target distance
	 */
	public static double calculateDistanceToTargetWithLine(double length)
	{
		// Determine the distance from the target
		// y = Ax^2 + Bx + C
		return (Constants.DISTANCE_A * length * length) + 
		       (Constants.DISTANCE_B * length) + 
				Constants.DISTANCE_C;
	}

	
}
