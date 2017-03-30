package org.usfirst.frc3824.Competition2017.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc3824.Competition2017.Constants;
import org.usfirst.frc3824.Competition2017.subsystems.Camera;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RIOCamera
{
	// Constant values for the image processing configuration
	final int MIN_CONTOUR_WIDTH     =  10;
	final int MAX_CONTOUR_WIDTH     = 640;
	final int MIN_CONTOUR_HEIGHT    =  10;
	final int MAX_CONTOUR_HEIGHT    = 480;
	final int MIN_COUNTOUR_AREA     = 100;
	final int MIN_CONTOUR_PERIMETER = 100;
	
	final int DEFAULT_H_MIN =  60;
	final int DEFAULT_H_MAX =  80;
	final int DEFAULT_S_MIN = 150;
	final int DEFAULT_S_MAX = 255;
	final int DEFAULT_V_MIN =  60;
	final int DEFAULT_V_MAX = 255;
	
	// Constant values for the camera
	final int IMAGE_WIDTH   = 640;
	final int IMAGE_HEIGHT  = 360;
	final int IMAGE_FPS     =  15;
	
	// Create the matrixes for image processing
	Mat source           = new Mat();
	Mat HSVimage         = new Mat();
	Mat contourImg       = new Mat();
	
	Mat output           = new Mat();
	Mat cameraFrameImage = new Mat();

	// Vectors holding contour and contour attributes
	Vector<Vector<Point> > outputContours;
	Vector<Rect> targetRectangles;
	
	// Storage for HSV values accessible via the SmartDashboard
	double H_min;
	double H_max;
	double S_min;
	double S_max;
	double V_min;
	double V_max;
	
	Rect largestTargetRect = new Rect();
	Rect secondLargestTargetRect = new Rect();
	
	boolean doImageProcessing = false;
	
	UsbCamera camera;
	CvSink cvSink;
	
	static RIOCamera instance = null;
	
	// Constructor - set everything up and start streaming as soon as the
	//	class is created
	private RIOCamera()
	{
		camera = CameraServer.getInstance().startAutomaticCapture();
		
		// Setup the camera
		// 640 x 480 will crop the 16x9 aspect ratio to 4x3
		// 640 x 360 will keep the 16x9 aspect ratio
		camera.setResolution(IMAGE_WIDTH, IMAGE_HEIGHT);  // Competition resolution
		camera.setFPS(IMAGE_FPS);
		
		// set everything for autonomous
		configAutonomous(true);
		initSmartDashboard();
		updateCameraConfig();

		// Setup the video stream
		cvSink = CameraServer.getInstance().getVideo();
	}
	
	public static RIOCamera getInstance()
	{
		if(instance == null) {
			instance = new RIOCamera();
		}

		return instance;
	}
	
	/*
	 * Thread to process the camera images and determine the targets based on the reflective tape
	 */
	public void startImageProcessing()
	{		
		new Thread(null, () -> 
		{
			int loopcounter = 0;
		
			// Continuously run the image processing thread
			while (doImageProcessing)
			{
				try 
				{
					SmartDashboard.putNumber("loopcounter", loopcounter++);
					
					// Grab a camera frame
					cvSink.grabFrame(source);
	
					if (!doImageProcessing) 
					{
	//					addCenterLine(source);
					}
					else
					{
						// Read the HSV values from the SmartDashboard
						H_min = SmartDashboard.getNumber("H Min", DEFAULT_H_MIN);
						H_max = SmartDashboard.getNumber("H Max", DEFAULT_H_MAX);
						S_min = SmartDashboard.getNumber("S Min", DEFAULT_S_MIN);
						S_max = SmartDashboard.getNumber("S Max", DEFAULT_S_MAX);
						V_min = SmartDashboard.getNumber("V Min", DEFAULT_V_MIN);
						V_max = SmartDashboard.getNumber("V Max", DEFAULT_V_MAX);
		
						// Blurs image from camera to make colors run together
						Imgproc.blur(source, cameraFrameImage, new Size(10, 10));
						
	//					outputStream.putFrame(cameraFrameImage);  // Remove after testing
					
						// Determine the reflective tape regions
						findTapeRegion(cameraFrameImage);
			
						// Find the region contours
						findRectangles(contourImg);
		
						// Find the two largest rectangles
						findTwoLargestRectangles();
						
		//				SmartDashboard.putNumber("Target A area",   largestTargetRect.area());
		//			
						SmartDashboard.putNumber("Target A X",      largestTargetRect.x);			
		//				SmartDashboard.putNumber("Target A Y",      largestTargetRect.y);
						SmartDashboard.putNumber("Target A width",  largestTargetRect.width);
		//				SmartDashboard.putNumber("Target A height", largestTargetRect.height);
		//			
		//				SmartDashboard.putNumber("Target B area",   secondLargestTargetRect.area());
		//			
						SmartDashboard.putNumber("Target B X",      secondLargestTargetRect.x);			
		//				SmartDashboard.putNumber("Target B Y",      secondLargestTargetRect.y);
						SmartDashboard.putNumber("Target B width",  secondLargestTargetRect.width);
		//				SmartDashboard.putNumber("Target B height", secondLargestTargetRect.height);
					
						double center = ((largestTargetRect.x       + (largestTargetRect.width / 2)) +
							         	 (secondLargestTargetRect.x + (secondLargestTargetRect.width / 2))) / 2;
					
						SmartDashboard.putNumber("Target Center", center);
					}
					
					// display centerline
	//				outputStream.putFrame(source);
				}
				catch (Exception exception)
				{
					System.err.println(exception);
				}
			}
		}, "RIOCamera").start();	// end of the thread declaration
	}
	
	/*
	 * Method to find the regions between the specified colors
	 */
	private void findTapeRegion(Mat image)
	{	
		// Initialize the minimum and maximum color range
		Scalar MINCOLOR = new Scalar(H_min, S_min, V_min);
		Scalar MAXCOLOR = new Scalar(H_max, S_max, V_max);
		
		// Converts image from BGR to HSV
		Imgproc.cvtColor(image, HSVimage, Imgproc.COLOR_BGR2HSV);

		// Makes the output image only show elements that are in between
		// the specified HSV min and max constants
		Core.inRange(HSVimage, MINCOLOR, MAXCOLOR, contourImg);
	}
	
	private void addCenterLine(Mat image)
	{
		int center = (int) SmartDashboard.getNumber("Line Position", 280);
		Imgproc.line(image, new Point(center, 0), new Point(center, image.rows()), new Scalar(255, 0, 0));
	}

	/*
	 * Method to find the regions in the image (as rectangles)
	 */
	private void findRectangles(Mat image)
	{	
		Mat hierarchy                  = new Mat();
		List<MatOfPoint> inputContours = new ArrayList<MatOfPoint>();

		targetRectangles = new Vector<Rect>();
		
		try
		{
			// Clear the target rectangles array
			targetRectangles.clear();
		}
		finally
		{
			
		}

		// Finds the contours on the contour image
		Imgproc.findContours(contourImg, inputContours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		
		// Find max contour area
		Iterator<MatOfPoint> each = inputContours.iterator();
		
		while (each.hasNext())
		{
			MatOfPoint wrapper = each.next();
			
			// Determine the bounding rectangle
			Rect boundingRetcangle = Imgproc.boundingRect(wrapper);
			
			if ((boundingRetcangle.width  > MIN_CONTOUR_WIDTH)  && (boundingRetcangle.width  < MAX_CONTOUR_WIDTH) &&
			    (boundingRetcangle.height > MIN_CONTOUR_HEIGHT) && (boundingRetcangle.height < MAX_CONTOUR_HEIGHT))
			{
				double area = Imgproc.contourArea(wrapper);

				// Ensure the area is large enough to be a target
				if (area > MIN_COUNTOUR_AREA)
				{
					targetRectangles.add(boundingRetcangle);
				}
			}
		}
	}
	
	/*
	 * Method to find the two largest rectangles
	 */
	private void findTwoLargestRectangles()
	{
		int largestAreaIndex       = -1;
		int secondLargestAreaIndex = -1;
		double largestArea;
		
		largestArea = 0;
		for (int index = 0; index < targetRectangles.size(); index++)
		{
			Rect rectangel = targetRectangles.get(index);
			
			double area = rectangel.area();
			
			if (area > largestArea)
			{
				largestArea = area;
				largestAreaIndex = index;
			}
		}
			
		largestArea = 0;
		for (int index = 0; index < targetRectangles.size(); index++)
		{
			if (index == largestAreaIndex)
				continue;
			
			Rect rectangel = targetRectangles.get(index);
			
			double area = rectangel.area();
			
			if (area > largestArea)
			{
				largestArea = area;
				secondLargestAreaIndex = index;
			}
		}
		
		if (largestAreaIndex != -1)
			largestTargetRect = targetRectangles.get(largestAreaIndex);
		if (secondLargestAreaIndex != -1)
			secondLargestTargetRect = targetRectangles.get(secondLargestAreaIndex);		
	}
	
	public void initSmartDashboard()
	{
		SmartDashboard.putBoolean("Camera Bright", !doImageProcessing);
		SmartDashboard.putNumber("Line Position", 280);

		// Setup the HSV minimum/maximum values on the SmartDashboard
		SmartDashboard.putNumber("H Min", DEFAULT_H_MIN);
		SmartDashboard.putNumber("H Max", DEFAULT_H_MAX);
		SmartDashboard.putNumber("S Min", DEFAULT_S_MIN);
		SmartDashboard.putNumber("S Max", DEFAULT_S_MAX);
		SmartDashboard.putNumber("V Min", DEFAULT_V_MIN);
		SmartDashboard.putNumber("V Max", DEFAULT_V_MAX);
	}
	
	public void updateSmartDashboard()
	{	
		// update camera settings
		boolean shouldBeBright = SmartDashboard.getBoolean("Camera Bright", false);
		boolean isCameraBright = !doImageProcessing;
		if (isCameraBright != shouldBeBright) 
		{
			// camera setting has changed, update
			configAutonomous(!shouldBeBright);
			SmartDashboard.putBoolean("Camera Bright", isCameraBright);
		}		
	}
	
	public void configAutonomous(boolean auto)
	{
		if(auto != doImageProcessing)
		{
			if (auto)
			{
				doImageProcessing = true;
				updateCameraConfig();
				startImageProcessing();
			} 
			else 
			{
				doImageProcessing = false; 	// this will stop the image processing thread
				updateCameraConfig();
			}
		}
	}
	
	private void updateCameraConfig()
	{
		if (doImageProcessing)
		{
			camera.setBrightness(0);
			camera.setExposureManual(0);
		} 
		else 
		{
			camera.setBrightness(Constants.CAMERA_BRIGHTNESS);
			camera.setExposureManual(Constants.CAMERA_EXPOSURE);
		}	
	}
}
