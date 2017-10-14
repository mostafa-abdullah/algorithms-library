package library.math;

import java.util.ArrayList;

public class PrimeFactorization {
	
	
	static int largestPrime[];
	static int end = 1000001;
	
	static void sieve2()
	{
		largestPrime = new int[end];
		
		for(int i=2; i<largestPrime.length; i++)
			if(largestPrime[i] == 0){
				for(int j=i; j < largestPrime.length; j+=i)
					largestPrime[j] = i;
			}
	}
	
	// O(logN) - returns arraylist containing distinct prime factors
	static ArrayList<Integer> factorize(int n)
	{
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		while(n > 1)
		{
			int prime = largestPrime[n];
			while(n % prime == 0)
				n /= prime;
			primeFactors.add(prime);
			
		}
		return primeFactors;
	}
	
	// O(logN) - returns array containing number of occurrences of prime factors
	// i.e: countPrimes[2] = number of divisions of n by 2
	static int[] factorizeWithCount(int n)
	{
		int countPrimes[] = new int[end];
		while(n > 1)
		{
			int prime = largestPrime[n];
			int countP = 0;
			while(n % prime == 0)
			{
				n /= prime;
				countP++;
			}
			countPrimes[prime] = countP;
		}
		return countPrimes;
	}
}
