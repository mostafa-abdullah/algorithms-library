package library.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphTraversal {

	
	static ArrayList<Integer> [] adjList;
	static boolean visited[];
	
	static void dfs(int node)
	{
		visited[node] = true;
		
		Stack<Integer> s = new Stack<Integer>();
		s.add(node);
		while(!s.isEmpty())
		{
			node = s.pop();
			for(int v : adjList[node])
				if(!visited[v])
				{
					visited[v] = true;
					s.add(v);
				}
			
		}
	}
	
	
	static void dfsRec(int node)
	{
		visited[node] = true;
		
		for(int v : adjList[node])
			if(!visited[v])
				dfs(v);		
	}
	
	
	static void bfs(int node)
	{
		visited[node] = true;
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(node);
		
		while(!q.isEmpty())
		{
			node = q.remove();
			for(int v : adjList[node])
				if(!visited[v])
				{
					visited[v] = true;
					q.add(v);
				}
		}
		
		
	}
}
