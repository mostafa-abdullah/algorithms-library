package library.math;

import java.util.ArrayList;
import java.util.Arrays;

public class Sieve {
	static ArrayList<Integer> primes;
	static boolean isPrime[];
	static int end = 1000001;
	static void sieve()
	{
		primes = new ArrayList<Integer>();
		isPrime = new boolean[end];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i=2; i<isPrime.length; i++)
			if(isPrime[i]){
				primes.add(i);
				for(int j=i*2; j < isPrime.length; j+=i)
					isPrime[j] = false;
			}
	}
}
