package library.graphs;

import java.util.ArrayList;

public class ArticulationPoints {

	static ArrayList<Integer> adjList[];
	static boolean [] visited, isArticulation;
	static int [] dfs_low, dfs_num,parent;
	static int dfs_counter, rootChildren, root;
	
	static void find(int u)
	{
		visited[u] = true;
		dfs_low[u] = dfs_num[u] = dfs_counter++;
		for(int v : adjList[u])
		{
			if(!visited[v])
			{
				parent[v] = u;
				if(u == root)
					rootChildren++;
				
				find(v);
				
				if(dfs_low[v] >= dfs_num[u]) // u is articulation point
					isArticulation[u] = true;
				
				if(dfs_low[v] > dfs_num[u])
				{
					// edge between u and v is a bridge
				}
				
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
				
				
				
			}
			else if(v != parent[u])
			{
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
			}
		}
	}
	
	public static void main(String[] args) {
		
		// dfs on all graph connected components
		// isArticulation[root] = rootChildren > 1;
	}
}
