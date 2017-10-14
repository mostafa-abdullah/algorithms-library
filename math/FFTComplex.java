package library.math;

// Computes the FTT with Complex data type, slower than double primitive types but simpler
public class FFTComplex {

	static int rev[];

	static void fft(Complex[] a, boolean inverse) {
		int n = a.length;

		// the bitwise inverse permutation
		for (int i = 0; i < n; i++) {
			int j = rev[i];
			if (i < j) {
				Complex tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			}
		}

		for (int len = 2; len <= n; len <<= 1) {
			double angle = 2 * Math.PI / len * (inverse ? -1 : 1);

			Complex wn = new Complex(Math.cos(angle), Math.sin(angle));

			for (int i = 0; i < n; i += len) {
				Complex w = new Complex(1, 0);

				for (int j = 0; j < len / 2; j++) {
					Complex u = a[i + j];
					Complex v = a[i + j + len / 2].multiply(w);

					a[i + j] = u.add(v);
					a[i + j + len / 2] = u.subtract(v);

					w = w.multiply(wn);
				}
			}
		}

		if (inverse)
			for (int i = 0; i < n; i++)
				a[i] = a[i].scale(1.0 / n);
	}

	static long[] multiply(long[] a, long[] b) {

		int N = 1;
		while (N < Math.max(a.length, b.length))
			N <<= 1;

		N <<= 1;

		rev = new int[N];

		calcRev(N, (int) Math.ceil(Math.log(N) / Math.log(2)));

		Complex[] aNorm = new Complex[N];
		Complex[] bNorm = new Complex[N];

		for (int i = 0; i < a.length; i++) {
			aNorm[i] = new Complex(a[i], 0);
		}
		for (int i = 0; i < b.length; i++) {
			bNorm[i] = new Complex(b[i], 0);
		}

		for (int i = a.length; i < N; i++) {
			aNorm[i] = new Complex(0, 0);
		}

		for (int i = b.length; i < N; i++) {
			bNorm[i] = new Complex(0, 0);
		}

		fft(aNorm, false);

		if (a == b) {
			// No need to make another fft call, multiply DFT(a) with itself
			for (int i = 0; i < N; i++) {
				aNorm[i] = aNorm[i].multiply(aNorm[i]);
			}
		} else {

			fft(bNorm, false);

			// DFT(a) x DFT(b)
			for (int i = 0; i < N; i++) {
				aNorm[i] = aNorm[i].multiply(bNorm[i]);
			}
		}

		// Get the inverse
		fft(aNorm, true);

		long[] res = new long[N];

		for (int i = 0; i < N; i++) {
			res[i] = (long) (aNorm[i].r + 0.5);
		}

		return res;
	}

	static void calcRev (int n, int log_n) {
		for (int i=0; i<n; ++i) {
			rev[i] = 0;
			for (int j=0; j<log_n; ++j)
				if ((i & (1<<j)) != 0)
					rev[i] |= 1<<(log_n-1-j);
		}
	}
}