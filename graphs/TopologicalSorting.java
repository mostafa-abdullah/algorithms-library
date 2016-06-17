package library.graphs;

import java.util.ArrayList;
import java.util.Collections;

public class TopologicalSorting {

	static ArrayList<Integer>[] adjList;
	static boolean [] visited;
	
	static ArrayList<Integer> topologicalSort(int n) // n = number of nodes
	{
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i=0; i<n; i++)
			if(!visited[i])
				dfs(i,ans);
		
		Collections.reverse(ans);
	
		return ans;
	}
	
	
	static void dfs(int n, ArrayList<Integer> ans)
	{
		visited[n] = true;
		for(int v : adjList[n])
			if(!visited[v])
				dfs(v, ans);
		
		ans.add(n);
	}
	
	
	
}
