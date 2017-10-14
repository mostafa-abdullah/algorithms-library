package library.dp;

public class BitonicTSP {
	static int dist[][];
	static int N;
	
	static int dp[][];
	
	static int solve(int p1, int p2) {
		int v = Math.max(p1, p2) + 1;
		
		if(v == N - 1)
			return dist[p1][v] + dist[v][p2];
		
		if(dp[p1][p2] != -1)
			return dp[p1][p2];
		
		int ch1 = dist[p1][v] + solve(v, p2);
		int ch2 = dist[v][p2] + solve(p1, v);
		
		return dp[p1][p2] = Math.min(ch1, ch2);
	}
	
}
