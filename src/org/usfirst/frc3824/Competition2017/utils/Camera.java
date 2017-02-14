package org.usfirst.frc3824.Competition2017.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera
{
	static int MIN_CONTOUR_WIDTH     =  10;
	static int MAX_CONTOUR_WIDTH     = 640;
	static int MIN_CONTOUR_HEIGHT    =  10;
	static int MAX_CONTOUR_HEIGHT    = 480;
	static int MIN_COUNTOUR_AREA     = 100;
	static int MIN_CONTOUR_PERIMETER = 100;
	
	// Create matrix for image data
	static Mat image           = new Mat();

	// Create contour output
	static List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	
	// Read the HSV values from the smartdashboard
	static double H_min =  60;
	static double H_max =  80;
	static double S_min = 150;
	static double S_max = 255;
	static double V_min =  60;
	static double V_max = 255;
	
	static Rect largestTargetRect;
	static Rect secondLargestTargetRect;
	
	private static Comparator<Rect> rectAreaCompare = (rect1, rect2) -> Double.compare(rect1.area(), rect2.area());
	
	/*
	 * Thread to process the camera images and determine the targets based on the reflective tape
	 */
	private static Thread thread = new Thread(() ->
	{	
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();

	    // Setup the camera
		camera.setResolution(640, 480);
		camera.setBrightness(0);
		camera.setExposureManual(0);

		// Setup the video stream
		CvSink   cvSink       = CameraServer.getInstance().getVideo();
		CvSource outputStream = CameraServer.getInstance().putVideo("Processed", 640, 480);

		// Setup the HSV minimum/maximum values on the smartdashboard
		SmartDashboard.putNumber("H Min", H_min);
		SmartDashboard.putNumber("H Max", H_max);
		SmartDashboard.putNumber("S Min", S_min);
		SmartDashboard.putNumber("S Max", S_max);
		SmartDashboard.putNumber("V Min", V_min);
		SmartDashboard.putNumber("V Max", V_max);

		// Initialize the minimum and maximum color range
		Scalar MINCOLOR = new Scalar(H_min, S_min, V_min);
		Scalar MAXCOLOR = new Scalar(H_max, S_max, V_max);
		
		// Continuously run the image processing thread
		while (!Thread.interrupted())
		{						
			// Grab a camera frame
			cvSink.grabFrame(image);

//			// Read the HSV values from the smartdashboard
//			H_min = SmartDashboard.getNumber("H Min",  60);
//			H_max = SmartDashboard.getNumber("H Max",  80);
//			S_min = SmartDashboard.getNumber("S Min", 150);
//			S_max = SmartDashboard.getNumber("S Max", 255);
//			V_min = SmartDashboard.getNumber("V Min",  60);
//			V_max = SmartDashboard.getNumber("V Max", 255);

			// Blurs image from camera to make colors run together
			Imgproc.blur(image, image, new Size(10, 10));
			
			// Converts image from BGR to HSV
			Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2HSV);

			// Filter specified HSV min and max constants
			Core.inRange(image, MINCOLOR, MAXCOLOR, image);
				
			// Finds the contours on the contour image
			Imgproc.findContours(image, contours, null, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
			
			// get bounding rect of contours
			Stream<Rect> rectangles = contours.stream().map(Imgproc::boundingRect)
			.filter(rect -> 
				rect.width > MIN_CONTOUR_WIDTH &&
				rect.width < MAX_CONTOUR_WIDTH &&
				rect.height > MIN_CONTOUR_HEIGHT &&
				rect.height < MAX_CONTOUR_HEIGHT
			).filter(rect ->
				rect.area() > MIN_COUNTOUR_AREA
			);

			
			// Find the two largest rectangles
			rectangles.max(rectAreaCompare).ifPresent(largestRect -> {
				largestTargetRect = largestRect;
				
				rectangles.filter(rect -> !largestRect.equals(rect)).max(rectAreaCompare).ifPresent(secondLargestRect -> {
					secondLargestTargetRect = secondLargestRect;
				});
			});
			
			
			
			SmartDashboard.putNumber("Target A area", largestTargetRect.area());
			SmartDashboard.putNumber("Target B area", secondLargestTargetRect.area());
			
			SmartDashboard.putNumber("Target A X", largestTargetRect.x);			
			SmartDashboard.putNumber("Target A Y", largestTargetRect.y);
			
			SmartDashboard.putNumber("Target B X", secondLargestTargetRect.x);
			SmartDashboard.putNumber("Target B Y", secondLargestTargetRect.y);
		
			// Show the contours
			outputStream.putFrame(image);
			
//			try
//			{
//				Thread.sleep(5000);
//			} 
//			catch (InterruptedException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	});
		
	/*
	 * Method to return the camera processing thread
	 */
	public static Thread GetThread()
	{
		return thread;
	}
}
