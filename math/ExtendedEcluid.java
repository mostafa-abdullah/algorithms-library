package library.math;

public class ExtendedEcluid {
	
	static int x, y, gcd;
	
	static void extendedEcluid(int a, int b) {
		if(b == 0)
		{
			x = 1;
			y = 0;
			gcd = a;
			return;
		}
		
		extendedEcluid(b, a % b);
		
		int xt = y;
		int yt = x - (a / b) * y;
		
		x = xt;
		y = yt;
	}
	
	public static void main(String[] args) {
		extendedEcluid(25, 18);
		
		/* ax + by = c
		 * ax + by = gcd(a, b)
		 * a = 25, b = 18
		 * to get the solution, multiply the second equation by c / gcd(a, b)
		 * all solutions:
		 * x = x + (b / d) * n
		 * y = y  - (a / d) * n
		*/
	}
}
