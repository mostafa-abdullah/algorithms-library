package library.math;

public class Determinant {

	public static double determinant(double[][] a) {
		final double eps = 1e-10;
		int n = a.length;
		double res = 1;
		boolean[] used = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			int p;
			for (p = 0; p < n; p++)
				if (!used[p] && Math.abs(a[p][i]) > eps)
					break;
			
			if (p >= n)
				return 0;
			
			res *= a[p][i];
			used[p] = true;
			double z = 1 / a[p][i];
			
			for (int j = 0; j < n; j++)
				a[p][j] *= z;
			
			for (int j = 0; j < n; ++j)
				if (j != p) {
					z = a[j][i];
					
					for (int k = 0; k < n; ++k)
						a[j][k] -= z * a[p][k];
				}
		}
		return res;
	}
}
