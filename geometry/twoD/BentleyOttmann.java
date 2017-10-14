package library.geometry.twoD;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class BentleyOttmann {

	static final double EPS = 1e-9;
	
	static ArrayList<Point> intersections(ArrayList<Line> lines) {
		ArrayList<Point> ans = new ArrayList<Point>();

		PriorityQueue<Event> q = new PriorityQueue<Event>();
		
		for(Line l : lines) {
			q.add(new Event(EventType.START, l));
			q.add(new Event(EventType.END, l));
		}
		
		TreeSet<Line> active = new TreeSet<Line>(new CompareY());
		
		while(!q.isEmpty()) {
			Event next = q.remove();
			
			if(next.type == EventType.START) {
				Line l1 = active.ceiling(next.l);
				Line l2 = active.floor(next.l);
				
				Point int1 = getIntersection(next.l, l1);
				Point int2 = getIntersection(next.l, l2);
				
				if(int1 != null && int1.x >= next.p.x)
					q.add(new Event(next.l, l1, int1));
				
				if(int2 != null && int2.x >= next.p.x)
					q.add(new Event(next.l, l2, int2));
				
				active.add(next.l);
			}
			else if(next.type == EventType.END) {
				active.remove(next.l);
				
				Line l1 = active.ceiling(next.l);
				Line l2 = active.floor(next.l);
				
				Point int1 = getIntersection(l1, l2);
				
				if(int1 != null && int1.x >= next.p.x)
					q.add(new Event(EventType.INTERSECTION, int1));
				
			}
			else {
				Line l1 = next.l;
				Line l2 = next.l2;
				
				if(l1.p1.y > l2.p2.y) {
					Line tmp = l1;
					l1 = l2;
					l2 = tmp;
				}
				
				Line la = active.ceiling(l2);
				Line lb = active.floor(l1);
				
				Point int1 = getIntersection(la, l2);
				Point int2 = getIntersection(lb, l1);
				
				if(int1 != null && int1.x >= next.p.x)
					q.add(new Event(la, l2, int1));
				
				if(int2 != null && int2.x >= next.p.x)
					q.add(new Event(lb, l1, int2));
			}
		}
		

		return ans;
	}
	

	static boolean isParallel(Line l1, Line l2) {
		return false;
	}
	
	static Point getIntersection(Line l1, Line l2) {
		if(isParallel(l1, l2))
			return null;
		
		return null;
	}
	
	static enum EventType {
		START,
		END,
		INTERSECTION
	}
	
	static class CompareY implements Comparator<Line> {

		@Override
		public int compare(Line l1, Line l2) {
			return l1.p1.y > l2.p1.y ? 1 : l1.p1.y < l2.p1.y ? -1 : 0;
		}
		
	}
	
	static class Event implements Comparable<Event> {
		EventType type;
		Point p;
		Line l;
		Line l2;
		
		public Event(EventType type, Point p) {
			this.type = type;
			this.p = p;
		}
		
		public Event(EventType type, Line l) {
			this.type = type;
			this.l = l;
			
			if(type == EventType.START)
				this.p = l.p1;
			else
				this.p = l.p2;
		}
		
		public Event(Line l, Line l2, Point p) {
			this.l = l;
			this.l2 = l2;
			this.p = p;
			this.type = EventType.INTERSECTION;
		}
		
		@Override
		public int compareTo(Event e) {
			return p.x > e.p.x ? 1 : p.x < e.p.x ? -1 : 0;
		}
		
	}

	
	static class Line {
		Point p1, p2;
		double a, b, c;
		public Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			
			if(p1.x == p2.x){
				c = -p1.x;
				b = 0;
			}
			else {
				b = 1;
				a = (p1.y - p2.y) / (p2.x - p1.x);
				c = -(a * p1.x + b * p1.y);
			}
			
		}
	}

	static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x; this.y = y;
		}
	}

}
