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
//import org.usfirst.frc3824.Competition2017.subsystems.Camera;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RIOCamera
{
	static int MIN_CONTOUR_WIDTH     =  10;
	static int MAX_CONTOUR_WIDTH     = 640;
	static int MIN_CONTOUR_HEIGHT    =  10;
	static int MAX_CONTOUR_HEIGHT    = 480;
	static int MIN_COUNTOUR_AREA     = 100;
	static int MIN_CONTOUR_PERIMETER = 100;
	
	// Create the matrixes for image processing
	static Mat source           = new Mat();
	static Mat HSVimage         = new Mat();
	static Mat contourImg       = new Mat();
	
	static Mat output           = new Mat();
	static Mat cameraFrameImage = new Mat();

	// Vectors holding contour and contour attributes
	static Vector<Vector<Point> > outputContours;
	static Vector<Rect> targetRectangles;
	
	// Read the HSV values from the SmartDashboard
	static double H_min =  60;
	static double H_max =  80;
	static double S_min = 150;
	static double S_max = 255;
	static double V_min =  60;
	static double V_max = 255;
	
	static Rect largestTargetRect       = new Rect();
	static Rect secondLargestTargetRect = new Rect();
	
	static boolean isCameraBright = false;
	
	/*
	 * Thread to process the camera images and determine the targets based on the reflective tape
	 */
	private static Thread thread = new Thread(() ->
	{		
		int loopcounter = 0;
	
		System.out.println("In Thread");
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		
		// Setup the camera
		camera.setResolution(640, 480);  // Competition resolution
		camera.setFPS(15);               // Default is 30
		
		camera.setBrightness(0);
		camera.setExposureManual(0);
		
		SmartDashboard.putBoolean("Camera Bright", false);
//		SmartDashboard.putNumber("Line Position", 280);

		// Setup the video stream
		CvSink   cvSink       = CameraServer.getInstance().getVideo();
		System.out.println("cvSink = " + cvSink);
		System.out.println("camera server name: " + CameraServer.getInstance().getServer().getName());
		
//		CvSource outputStream = CameraServer.getInstance().putVideo("Processed", 640, 480);

		// Setup the HSV minimum/maximum values on the SmartDashboard
		SmartDashboard.putNumber("H Min", H_min);
		SmartDashboard.putNumber("H Max", H_max);
		SmartDashboard.putNumber("S Min", S_min);
		SmartDashboard.putNumber("S Max", S_max);
		SmartDashboard.putNumber("V Min", V_min);
		SmartDashboard.putNumber("V Max", V_max);

		// Continuously run the image processing thread
//		while (!Thread.interrupted())
		while (true)
		{
			try 
			{
				SmartDashboard.putNumber("loopcounter", loopcounter++);
				
				// update camera settings
				boolean shouldBeBright = SmartDashboard.getBoolean("Camera Bright", false);
				if (isCameraBright != shouldBeBright) 
				{
					// camera setting has changed, update
					updateCameraSettings(camera, shouldBeBright);
					
					// Indicate that the camera is set to bright mode
					isCameraBright = shouldBeBright;
				}
				
				// System.out.println("Loop Counter: " + loopcounter);
				
				// Grab a camera frame
				if (cvSink.grabFrame(source) == 0)
				{
					System.out.println("cv failed to get frame");
					continue;
				}
				System.out.print(".");

				if (isCameraBright) 
				{
//					addCenterLine(source);
				}
				else
				{
					// Read the HSV values from the SmartDashboard
					H_min = SmartDashboard.getNumber("H Min",  60);
					H_max = SmartDashboard.getNumber("H Max",  80);
					S_min = SmartDashboard.getNumber("S Min", 150);
					S_max = SmartDashboard.getNumber("S Max", 255);
					V_min = SmartDashboard.getNumber("V Min",  60);
					V_max = SmartDashboard.getNumber("V Max", 255);
	
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
				System.out.println("In exception");
				System.err.println(exception);
			}
		}
	});
	
	/*
	 * Method to find the regions between the specified colors
	 */
	private static void findTapeRegion(Mat image)
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
	
	private static void addCenterLine(Mat image)
	{
		int center = (int) SmartDashboard.getNumber("Line Position", 280);
		Imgproc.line(image, new Point(center, 0), new Point(center, image.rows()), new Scalar(255, 0, 0));
	}

	/*
	 * Method to find the regions in the image (as rectangles)
	 */
	private static void findRectangles(Mat image)
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
	static void findTwoLargestRectangles()
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
	
	public static void updateCameraSettings(UsbCamera camera, boolean bright)
	{
		if (bright)
		{
			camera.setBrightness(Constants.CAMERA_BRIGHTNESS);
			camera.setExposureManual(Constants.CAMERA_EXPOSURE);
		} 
		else 
		{
			camera.setBrightness(0);
			camera.setExposureManual(0);
		}
	}
	
	/*
	 * Method to return the camera processing thread
	 */
	public static Thread GetThread()
	{
		return thread;
	}
}
