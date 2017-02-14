package org.usfirst.frc3824.Competition2017.utils;

import java.util.Comparator;

public class Line
{
	public Point  p1;
	public Point  p2;
	public double angle;
	public double length;

	public Line()
	{
		p1           = new Point();
		p2           = new Point();
		angle        = -9999;
		length       = -9999;
	}
	
	/**
	 * Method to get the average of the x points in a line
	 */
	public double midpointX() {
		return Math.abs(p2.x + p1.x) / 2.0;
	}
			
	public static Comparator<Line> LineXComparator = new Comparator<Line>()
	{
		public int compare(Line line1, Line line2)
		{
			double line1_p1x = line1.p1.x;
			double line2_p1x = line2.p1.x;

			// ascending order: line1_p1x > line2_p1x returns +1
			if (line1_p1x > line2_p1x)
				return 1;
			else if (line2_p1x > line1_p1x)
				return -1;
			else
				return 0;
		}
	};

	public static Comparator<Line> LineAngleComparator = new Comparator<Line>()
	{
		public int compare(Line line1, Line line2)
		{
			// ascending order: line1_p1x > line2_p1x returns +1
			if (line1.angle > line2.angle)
				return 1;
			else if (line2.angle > line1.angle)
				return -1;
			else
				return 0;
		}
	};
}
