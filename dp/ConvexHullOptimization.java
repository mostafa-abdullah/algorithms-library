package library.dp;

public class ConvexHullOptimization {
	long[] A;
	long[] B;
	int len;
	int ptr;

	public ConvexHullOptimization(int N)
	{
		A = new long[N];
		B = new long[N];
		
		len = 0;
	}
	
	// a descends
	public void addLine(long a, long b) {
		while (len >= 2 && (B[len - 2] - B[len - 1]) * (a - A[len - 1]) >= (B[len - 1] - b) * (A[len - 1] - A[len - 2])) {
			--len;
		}
		A[len] = a;
		B[len] = b;
		++len;
	}

	// x ascends
	public long query(long x) {
		ptr = Math.min(ptr, len - 1);
		while (ptr + 1 < len && A[ptr + 1] * x + B[ptr + 1] <= A[ptr] * x + B[ptr]) {
			++ptr;
		}
		return A[ptr] * x + B[ptr];
	}
}