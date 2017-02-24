package org.usfirst.frc3824.Competition2017.utils;

import java.util.Comparator;

public class Target
{
	public int centerX;
	public int centerY;
	public int height;
	public int width;
	public int area;
	
	public static Comparator<Target> XComparator = new Comparator<Target>()
	{
		public int compare(Target t1, Target t2)
		{
			// ascending order: line1_p1x > line2_p1x returns +1
			if (t1.centerX > t2.centerX)
				return 1;
			else if (t2.centerX > t1.centerX)
				return -1;
			else
				return 0;
		}
	};

	public static Comparator<Target> AreaComparatorDescending = new Comparator<Target>()
	{
		public int compare(Target t1, Target t2)
		{
			// descending order: target1.area < target2.area returns +1
			if (t1.area < t2.area)
				return 1;
			else if (t2.area < t1.area)
				return -1;
			else
				return 0;
		}
	};

	public static Comparator<Target> AreaComparatorAscending = new Comparator<Target>()
	{
		public int compare(Target t1, Target t2)
		{
			// ascending order: target1.area > target2.area returns +1
			if (t1.area > t2.area)
				return 1;
			else if (t2.area > t1.area)
				return -1;
			else
				return 0;
		}
	};
}
