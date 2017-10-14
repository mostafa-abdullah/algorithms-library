package library.geometry.twoD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class IntersectionAreaOfConvexPolygons {
	 private static class Event implements Comparable<Event>
	    {
	        public double y;
	        public int mask_delta;
	 
	        public int compareTo(Event b)
	        {
	            if (y < b.y) return -1;
	            else if (y > b.y) return 1;
	            else return mask_delta - b.mask_delta;
	        }
	 
	        public Event(double y, int mask_delta)
	        {
	            this.y = y;
	            this.mask_delta = mask_delta;
	        }
	    }
	 
	    public double overlap(Point[] polygon1, Point[] polygon2)
	    {
	        int[] V = {0, 0};
	        SortedSet<Double> xs = new TreeSet<Double>();
	        double ans = 0.0;
	 
	        for (int i = 0; i < polygon1.length - 1; i++) {
	        	xs.add(polygon1[i].x);
	        }
	        
	        for (int i = 0; i < polygon2.length - 1; i++) {
	        	xs.add(polygon2[i].x);
	        }
	        
	        for (int i = 0; i < polygon1.length - 1; i++)
	            for (int j = 0; j < polygon2.length - 1; j++)
	            {
	                Point cut = new Point(0, 0);
	                if (intersect(polygon1[i], polygon1[i+1],
	                              polygon2[j], polygon2[j+1],
	                              cut))
	                {
	                    xs.add(cut.x);
	                }
	            }
	 
	        Double[] xsa = xs.toArray(new Double[0]);
	        int E = xsa.length;
	        for (int i = 0; i + 1 < E; i++)
	        {
	            double x = (xsa[i] + xsa[i + 1]) * 0.5;
	            Point sweep0 = new Point(x, 0);
	            Point sweep1 = new Point(x, 1);
	            ArrayList<Event> events = new ArrayList<Event>();
	 
	            for (int j = 0; j < polygon1.length - 1; j++)
                {
                    Point cut = new Point(0, 0);
                    intersect(polygon1[j], polygon1[j + 1], sweep0, sweep1, cut);
                    double y = cut.y;
                    double x0 = polygon1[j].x;
                    double x1 = polygon1[j + 1].x;
                    if (x0 < x && x1 > x)
                    {
                        events.add(new Event(y, 1));
                    }
                    else if (x0 > x && x1 < x)
                    {
                        events.add(new Event(y, -1));
                    }
                }
	            
	            for (int j = 0; j < polygon2.length; j++)
                {
                    Point cut = new Point(0, 0);
                    intersect(polygon2[j], polygon2[j + 1], sweep0, sweep1, cut);
                    double y = cut.y;
                    double x0 = polygon2[j].x;
                    double x1 = polygon2[j + 1].x;
                    if (x0 < x && x1 > x)
                    {
                        events.add(new Event(y, 2));
                    }
                    else if (x0 > x && x1 < x)
                    {
                        events.add(new Event(y, -2));
                    }
                }
	            Collections.sort(events);
	 
	            double a = 0.0;
	            int mask = 0;
	            for (int j = 0; j < events.size(); j++)
	            {
	                if (mask == 3)
	                    a += events.get(j).y - events.get(j - 1).y;
	                mask += events.get(j).mask_delta;
	            }
	 
	            ans += a * (xsa[i + 1] - xsa[i]);
	        }
	        return ans;
	    }
	    
	    static boolean intersect(Point p1, Point p2, Point p3, Point p4, Point intersection) {
	    	
	    	// check if lines (p1, p2) and (p3, p4) are intersecting, if so, set intersection co-ordinates
	    	// and return true
	    	
	    	return false;
	    }
	    
	    static class Point {
			double x, y;

			public Point(double x, double y) {
				this.x = x; this.y = y;
			}
		}
}
