package library.graphs;

import java.util.ArrayList;
import java.util.Stack;

public class SCC {
	
	
	static ArrayList<Integer> adjList[];
	static boolean visited[];
	static int [] dfs_num, dfs_low;
	static int dfs_counter, num_SCC, N;
	static Stack<Integer> s;
	
	static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = dfs_counter++;
		visited[u] = true;
		s.push(u);
		
		for(int v : adjList[u])
		{
			if(dfs_num[v] == -1){
				tarjanSCC(v);
			}
			if(visited[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
			
		}
		
		if(dfs_low[u] == dfs_num[u])
		{
			// u is a root of a SCC
			num_SCC++;
			while(true)
			{
				int v = s.pop();
				visited[v] = false;
				if(v == u)
					break;
					
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=0; i<N; i++)
		{
			if(dfs_num[i] == -1)
				tarjanSCC(i);
		}
	}
	
	
}
