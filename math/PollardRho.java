package library.math;

public class PollardRho {
	
	static long modMul(long a, long b, long mod)
	{
		long x = 0, y = a % mod;
		
		while(b > 0)
		{
			if((b & 1) == 1)
				x = (x + y) % mod;
			y = (y * 2) % mod;
			
			b >>= 1;
		}
		
		return x % mod;
	}
	
	static long gcd(long a, long b)
	{
		if(b == 0)
			return a;
		
		return gcd(b, a%b);
	}
	
	// returns a random divisor
	static long pollard(long n)
	{
		
		if(n == 1)
			return n;
		
		if(n % 2 == 0)
			return 2;
		
		// random seeding
		long x = (long)(Math.random() * (n-2)) + 2;
		long y = x;
		
		long c = (long)(Math.random() * (n-1)) + 1;
		long d = 1;
		
		while(d == 1) {
			// Tortoise move
			x = (modMul(x, x, n) + c + n) % n;
			
			// Hare move
			y = (modMul(y, y, n) + c + n) % n;
			y = (modMul(y, y, n) + c + n) % n;
			
			d = gcd(Math.abs(x - y), n);

			if(d == n) {
				// The algorithm failed for the randomly chosen x, c. Try again.
				return pollard(n);
			}
		}
		
		return d;
	}
	
	static long pollard2(long n)
	{
		int i = 0, k = 2;
		long x = 3, y = 3;
		
		while(true) {
			i++;
			x = (modMul(x, x, n) + n - 1) % n;
			
			long d = gcd(Math.abs(x - y), n);
			
			if(d != 1 && d != n)
				return d;
			
			if(i == k) {
				y = x;
				k *= 2;
			}
		}
	}
	
	public static void main(String[] args) {
		long n = 16;
		
		System.out.printf("One of the divisors of %d is: %d\n", n, pollard2(n));
	}
	
}
