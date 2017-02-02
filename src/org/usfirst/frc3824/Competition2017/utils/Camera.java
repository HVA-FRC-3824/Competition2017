package org.usfirst.frc3824.Competition2017.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	private static Thread thread = new Thread(() ->
	{
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();

		SmartDashboard.putNumber("H Min", 70);
		SmartDashboard.putNumber("H Max", 80);
		SmartDashboard.putNumber("S Min", 150);
		SmartDashboard.putNumber("S Max", 255);
		SmartDashboard.putNumber("V Min", 60);
		SmartDashboard.putNumber("V Max", 255);

		camera.setResolution(640, 480);
		camera.setBrightness(0);
		camera.setExposureManual(0);

		CvSink cvSink = CameraServer.getInstance().getVideo();
		CvSource outputStream = CameraServer.getInstance().putVideo("Processed", 640, 480);

		Mat source = new Mat();
		Mat output = new Mat();
		Mat cameraFrameImage = new Mat();
		Mat contourImg = new Mat();
		Mat hierarchy = new Mat();

		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

		while (!Thread.interrupted())
		{
			cvSink.grabFrame(source);

			double H_min = SmartDashboard.getNumber("H Min", 0);
			double H_max = SmartDashboard.getNumber("H Max", 90);
			double S_min = SmartDashboard.getNumber("S Min", 0);
			double S_max = SmartDashboard.getNumber("S Max", 100);
			double V_min = SmartDashboard.getNumber("V Min", 0);
			double V_max = SmartDashboard.getNumber("V Max", 100);

			// Blurs image from camera to make colors run together
			Imgproc.blur(source, cameraFrameImage, new Size(10, 10));

			// Converts image from BGR to HSV
			Imgproc.cvtColor(cameraFrameImage, cameraFrameImage, Imgproc.COLOR_BGR2HSV);

			// Makes the output image only show elements that are in between
			// the specified HSV min and max constants
//			Core.inRange(cameraFrameImage, new Scalar(H_min, S_min, V_min), new Scalar(H_max, S_max, V_max),
//					output);

			// Makes the contour image only show elements that are in
			// between the specified HSV min and max constants
			Core.inRange(cameraFrameImage, new Scalar(H_min, S_min, V_min), new Scalar(H_max, S_max, V_max),
					contourImg);

			// Finds the contours on the contour image
			Imgproc.findContours(contourImg, contours, hierarchy, Imgproc.RETR_EXTERNAL,
					Imgproc.CHAIN_APPROX_SIMPLE);

			for (MatOfPoint contour : contours)
			{
				Rect rect = Imgproc.boundingRect(contour);

				if (rect.height > 28)
				{
					System.out.println(rect.x + "," + rect.y + "," + rect.height + "," + rect.width);
					
					// draw rectangle around contour areas with large height
					Imgproc.rectangle(contourImg, new Point(rect.x, rect.height), new Point(rect.y, rect.width),
							new Scalar(0, 0, 255));
				}
			}

			// Show the contours
			outputStream.putFrame(contourImg);
		}

	});
	
	public static Thread GetThread()
	{
		return thread;
	}
}
