package library.graphs;

public class APSP {
	
	// Dummy value = INF
	static int[][] adjMat;
	static int N;
	
	
	// computes all pairs shortest paths in O(V^3)
	static void floydWarshall()
	{
		for(int k = 0; k < N; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					adjMat[i][j] = Math.min(
							adjMat[i][j], 
							adjMat[i][k] + adjMat[k][j]);
	}
	
	
	
	static boolean [][] adjMat2;
	
	// adjMat2[i][j] is true if i is connected to j (directly or indirectly)
	static void transitiveClosure()
	{
		for(int k = 0; k < N; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					adjMat[i][j] |= (adjMat[i][k] & adjMat[k][j]);
	}
	
	
	// adjMat[i][j] contains the minimum highest cost on paths from i to j
	static void minimax()
	{
		for(int k = 0; k < N; k++)
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					adjMat[i][j] = Math.min(
							adjMat[i][j], 
							Math.max(adjMat[i][k], adjMat[k][j]));
	}
	
	
	// if the graph contains cycles, returns the smallest non negative cycle, otherwise returns -1
	static int cheapestCycle()
	{
		// Assuming that adjMat[][] is initialized with INF
		
		floydWarshall();
		
		int min = (int) 1e9;
		for(int i=0; i < N; i++)
			if(adjMat[i][i] >= 0)
				min = Math.min(min, adjMat[i][i]);
		
		return min == (int) 1e9 ? -1 : min;
	}
	
	
	// returns true if the graph has negative cycles
	static boolean hasNegativeCycle()
	{

		// Assuming that adjMat[][] is initialized with INF
		
		floydWarshall();
		
		for(int i=0; i < N; i++)
			if(adjMat[i][i] < 0)
				return true;
		
		return false;
		
	}
	
	// returns the maximum shortest path between any 2 nodes in the graph
	static int graphDiameter()
	{
		floydWarshall();
		
		int max = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0;  j < N; j++)
				if(adjMat[i][j] != (int) 1e9)
					max = Math.max(max, adjMat[i][j]);

		return max;
	}
	
	
	// returns true if node i is in the same connected component as node j
	// i.e.: there is a path from node i to node j and vice versa
	static boolean sameConnectedComponent(int i, int j)
	{
		transitiveClosure();
		return adjMat2[i][j] && adjMat2[j][i];
	}
}
