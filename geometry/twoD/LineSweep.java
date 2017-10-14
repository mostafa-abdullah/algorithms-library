package library.geometry.twoD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class LineSweep {

	
	static double closestPair(Point[] p) {
		if(p.length <= 1)
			return 0;

		Arrays.sort(p, new XCompare());

		TreeSet<Point> set = new TreeSet<Point>(new YCompare());

		double min = dist(p[0], p[1]);
		set.add(p[0]);
		set.add(p[1]);

		for(int i=2; i<p.length; i++) {
			SortedSet<Point> subset = set.subSet(new Point(0, p[i].y - min), new Point(0, p[i].y + min));

			for(Point p2 : subset) {
				double dist = dist(p[i], p2);
				if(dist < min)
					min = dist;
			}

			set.add(p[i]);

			ArrayList<Point> rem = new ArrayList<Point>();
			for(Point p2 : set) {
				if(p[i].x - p2.x > min)
					rem.add(p2);
			}

			for(Point p2 : rem)
				set.remove(p2);
		}

		return min;
	}

	static double dist(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}

	static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x; this.y = y;
		}
	}

	static class XCompare implements Comparator<Point> {

		@Override
		public int compare(Point p1, Point p2) {
			return p1.x > p2.x ? 1 : p1.x < p2.x ? -1 : 0;
		}

	}

	static class YCompare implements Comparator<Point> {

		@Override
		public int compare(Point p1, Point p2) {
			return p1.y > p2.y ? 1 : p1.y < p2.y ? -1 : 0;
		}

	}
	
}
