package library.math;

public class MatrixExpon {


	static long[][] matrixMul(long[][] a, long[][] b) {
		long[][] ans = new long[a.length][b[0].length]; 
		
		for(int i = 0; i < a.length; i++)
			for(int j = 0; j < b[0].length; j++)
				for(int k = 0; k < b.length; k++) {
					ans[i][j] += a[i][k] * b[k][j];
				}
				
		return ans;
	}
	
	static long[][] matrixPow(long[][] base, int p) {
		long[][] ans = new long[base.length][base.length];
		
		for(int i = 0; i < base.length; i++)
			ans[i][i] = 1;
		
		while(p > 0) {
			if((p & 1) == 1)
				ans = matrixMul(ans, base);
			
			base = matrixMul(base, base);
			
			p >>= 1;
		}
		
		return ans;
	}
}
