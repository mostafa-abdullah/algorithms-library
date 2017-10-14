package library.graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class LCA_log {
	static ArrayList<Integer> adjList[];
	static int N;
	static int parent[];
	static int level[];
	static int P[][];
	
	
	//O(N)
	static void dfs(int node, int depth)
	{
		level[node] = depth;
		for(int v : adjList[node])
			if(v != parent[node] && parent[v] == -1)
			{
				parent[v] = node;
				dfs(v,depth+1);
			}
		
	}
	
	
	// O(NlogN)
	static void process()
	{
		parent = new int[N];
		level = new int[N];
		Arrays.fill(parent, -1);
		dfs(0,0);
		P = new int[N][(int)Math.ceil(Math.log(N)/Math.log(2))];
		
		for(int i=0; i<N; i++)
			Arrays.fill(P[i], -1);
		
		for(int i = 0; i < N; i++)
			P[i][0] = parent[i];
		
		for(int j = 1; 1 << j < N; j++)
			for(int i = 0; i < N; i++){
				if(P[i][j-1] == -1)
					P[i][j] = i;
				else
					P[i][j] = P[P[i][j-1]][j-1];
			}
		
	}
	
	
	//O(logH) = O(loglogN) if tree is balanced = O(logN) if linear tree
	static int LCA_query(int u, int v)
	{
		if(level[u] < level[v])
		{
			int tmp = u;
			u = v;
			v = tmp;
		}
		
		int log = (int) (Math.log(level[u])/Math.log(2));
		
		for(int i = log; i>=0; i--)
			if(level[u] - (1 << i) >= level[v])
				u = P[u][i];
		
		if(u == v)
			return u;
		
		for(int i = log; i>=0; i--)
			if(P[u][i] != -1 && P[u][i] != P[v][i])
			{
				u = P[u][i]; v = P[v][i];
			}
		
		return parent[u];
			
	}
}
