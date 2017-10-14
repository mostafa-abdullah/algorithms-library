package library.geometry.twoD;

import java.util.Scanner;

public class CirclesIntersection {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/**
		 *  Circles are represented by (x,y) coordinates of center and radius r
		 *  Circle 1 ==> (x1,y1) , r1
		 *  Circle 2 ==> (x2,y2) , r2
		 */
		
		
		int x1 = sc.nextInt();
		int y1 = sc.nextInt();
		int r1 = sc.nextInt();

		int x2 = sc.nextInt();
		int y2 = sc.nextInt();
		int r2 = sc.nextInt();

		
		// distance between the 2 centers
		double d =Math.sqrt((long)(y2-y1)*(y2-y1) + (long)(x2-x1)*(x2-x1));
		double pi = Math.acos(-1);

		if(d>r1+r2){
			// circles don't intersect
			System.out.println(0);
			return;
		}
		if(d +r1 <= r2){
			// circle 1 is subset of circle 2
			System.out.println(pi*r1*r1);
			return;
		}
		if(d+r2 <= r1){
			// circle 2 is subset of circle 1
			System.out.println(pi*r2*r2);
			return;
		}
		double a2 = Math.acos(r2*1.0/2/d + d/2/r2 - r1*1.0/2/r2/d*r1);
		double ans2 = r2*a2*r2 - r2*Math.cos(a2)*r2*Math.sin(a2);

		double a1 = Math.acos(r1*1.0/2/d + d/2/r1 - r2*1.0/2/r1/d*r2);
		double ans1 = r1*a1*r1 - r1*Math.sin(a1)*r1*Math.cos(a1);

		System.out.println(ans1+ans2);
	}
}
