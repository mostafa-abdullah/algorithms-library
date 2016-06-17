package library.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteCheck {
	static ArrayList<Integer> adjList[];
	static boolean visited[];
	static int[] color; // Contains the colors of each node after calling bipartiteCheck()
	
	static boolean bipartiteCheck()
	{
		boolean bipartite = true;
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(0);
		visited[0] = true;
		while(!q.isEmpty() && bipartite)
		{
			int node = q.remove();
			for(int v : adjList[node])
				if(!visited[v])
				{
					visited[v] = true;
					color[v] = 1 - color[node];
					q.add(v);
				}
				else if(color[v] == color[node])
					bipartite = false;
			
		}
		
		return bipartite;
	}
	
	
	
}

