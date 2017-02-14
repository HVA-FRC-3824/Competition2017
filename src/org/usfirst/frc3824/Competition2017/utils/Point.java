package org.usfirst.frc3824.Competition2017.utils;

public class Point {
	public double x;
	public double y;

	public Point()
	{
		this.x = -9999;
		this.y = -9999;
	}

	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}
}
