package library.geometry.threeD;
import java.util.Scanner;



public class PlaneEquation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		
		
			Point p[] = new Point[3];
			for(int i=0; i<3; i++)
				p[i] = new Point(sc.nextInt(),sc.nextInt(),sc.nextInt());
			
			getPlaneEquation(p[0],p[1],p[2]);
			
		}
	
	
	// A plane is represented by the equation ax + by + cx = d
	
	static long a,b,c,d;
	
	static void getPlaneEquation(Point p1, Point p2, Point p3)
	{
		Vector v1 = new Vector(p1,p2);
		Vector v2 = new Vector(p2,p3);
		
		Vector cross = v1.cross(v2);
		
		a = cross.x;
		b = cross.y;
		c = cross.z;
		
		d = a*p1.x + b*p2.y + c*p3.z;
	}
	
	
	static class Vector{
		int x,y,z;
		public Vector(Point a, Point b)
		{
			x = b.x - a.x;
			y = b.y - a.y;
			z = b.z - a.z;
		}
		public Vector(int a, int b, int c)
		{
			x = a;
			y = b;
			z = c;
		}
		
		Vector cross(Vector v)
		{
			return new Vector(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
		}
	}
	
	
	
	static class Point{
		int x,y,z;
		public Point(int a, int b, int c) { x = a; y = b; z = c;}
	}
}
