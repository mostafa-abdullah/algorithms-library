package library.math;

// Computes the FTT with primitive double type, faster than references objects
public class FTTDouble {
	
	static void fft(double[] ar, double[] ai, boolean inverse) {
		int n = ar.length;
		
		for (int i = 1, j = 0; i < n; i++) {
		
			int bit = n >> 1;
			for (; j >= bit; bit >>= 1)
				j -= bit;
			j += bit;
			
			if (i < j) {
				double tmp = ar[i];
				ar[i] = ar[j];
				ar[j] = tmp;
				
				tmp = ai[i];
				ai[i] = ai[j];
				ai[j] = tmp;
			}
		}

		for (int len = 2; len <= n; len <<= 1) {
			double angle = 2 * Math.PI / len * (inverse ? -1 : 1);
			
			int len2 = len>>1;
			
			double wnr = Math.cos(angle);
			double wni = Math.sin(angle);
			
			for (int i = 0; i < n; i += len) {
				
				double wr = 1;
				double wi = 0;
				
				for (int j = 0; j < len2; j++) {
					double ur = ar[i+j];
					double ui = ai[i+j];
					
					double vr = realMult(ar[i+j+len2], ai[i+j+len2], wr, wi);
					double vi = imagMult(ar[i+j+len2], ai[i+j+len2], wr, wi);
					
					ar[i + j] = ur + vr;
					ai[i + j] = ui + vi;
					
					ar[i + j + len / 2] = ur - vr;
					ai[i + j + len / 2] = ui - vi;
					
					
					double twr = wr;
					double twi = wi;
					
					wr = realMult(twr, twi, wnr, wni);
					wi = imagMult(twr, twi, wnr, wni);
				}
			}

		}

		if (inverse)
			for (int i = 0; i < n; i++){
				ar[i] /= n;
				ai[i] /= n;
			}
	}
	
	static double realMult(double ar, double ai, double br, double bi)
	{
		return ar * br - ai * bi;
	}
	
	static double imagMult(double ar, double ai, double br, double bi)
	{
		return ar * bi + ai * br;
	}

	static long[] multiply(long[] a, long[] b) {

		int N = 1;
		while (N < Math.max(a.length, b.length))
			N <<= 1;

		N <<= 1;
		
		double ar[] = new double[N];
		double ai[] = new double[N];
		
		double br[] = new double[N];
		double bi[] = new double[N];
		
		for (int i = 0; i < a.length; i++) {
			ar[i] = a[i];
		}
		for (int i = 0; i < b.length; i++) {
			br[i] = b[i];
		}

		fft(ar, ai, false);
		
		if(a == b)
		{
			for (int i = 0; i < N; i++) {
				double real = ar[i];
				double imag = ai[i];
				ar[i] = realMult(real, imag, real, imag);
				ai[i] = imagMult(real, imag, real, imag);
			}
		}
		else{
			fft(br, bi, false);
			for (int i = 0; i < N; i++) {
				double real = ar[i];
				double imag = ai[i];
				ar[i] = real * br[i] - imag * bi[i];
				ai[i] = real * bi[i] + imag * br[i];
			}
		}

		fft(ar, ai, true);

		long[] res = new long[N];

		for (int i = 0; i < N; i++) {
			res[i] = (long) (ar[i] + 0.5);
		}
		
		return res;
	}
}
