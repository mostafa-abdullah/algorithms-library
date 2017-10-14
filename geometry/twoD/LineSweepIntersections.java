package library.geometry.twoD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class LineSweepIntersections {
	
	
	static ArrayList<Point> intersectionsPerpendicular(ArrayList<Line> lines) {
		ArrayList<Point> ans = new ArrayList<Point>();

		TreeMap<Integer, Integer> active = new TreeMap<Integer, Integer>();

		ArrayList<Event> events = new ArrayList<Event>();

		for(Line l : lines) {
			if(l.y1 != l.y2) {
				events.add(new Event(EventType.VERTICAL, l, l.x1));
			}
			else {
				events.add(new Event(EventType.HORIZONTAL_START, l, l.x1));
				events.add(new Event(EventType.HORIZONTAL_END, l, l.x2));
			}
		}

		Collections.sort(events);

		for(Event e : events) {
			switch (e.type) {
			case HORIZONTAL_START: { 
				Integer count = active.get(e.line.y1);
				active.put(e.line.y1, count == null ? 1 : count + 1);
			}
			break;
			case HORIZONTAL_END: {
				Integer count = active.get(e.line.y1);
				if(count == 1)
					active.remove(e.line.y1);
				else
					active.put(e.line.y1, count - 1);
			}
			break;
			case VERTICAL: 
				SortedMap<Integer, Integer> subMap = active.subMap(e.line.y1, e.line.y2 + 1);
				
				for(Entry<Integer, Integer> entry : subMap.entrySet()) {
					for(int i = 0; i < entry.getValue(); i++){
						ans.add(new Point(e.line.x1, entry.getKey()));
					}
				}
				
				break;
			}
		}

		return ans;
	}

	static enum EventType {
		HORIZONTAL_START,
		VERTICAL,
		HORIZONTAL_END
	}

	static class Event implements Comparable<Event> {
		EventType type;
		Line line;
		int x;

		public Event(EventType type, Line line, int x){
			this.type = type;
			this.line = line;
			this.x = x;
		}

		@Override
		public int compareTo(Event e) {
			if(x == e.x)
				return type.compareTo(e.type);

			return x - e.x;
		}
	}

	static class Line {
		int x1, y1, x2, y2;

		public Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
