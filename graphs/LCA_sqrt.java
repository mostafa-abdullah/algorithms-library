package library.graphs;

import java.util.ArrayList;

public class LCA_sqrt {
	
	
	static ArrayList<Integer> adjList[];
	static int [] parentNode;
	static int [] parentSection;
	static int [] level;
	static int N;
	static int H; // height of the tree
	static int sqrtH; // sqrt(H)
	
	
	
	
	static void preprocess()
	{
		preprocess(0,0);
	}
	
	static void preprocess(int node, int depth)
	{
		level[node] = depth;
		for(int v : adjList[node])
		{
			if(v == parentNode[node] || parentNode[v] != -1)
				continue;
			parentNode[v] = node;
			preprocess(v, depth+1);
		}
	}
	
	// precompute in O(N)
	static void dfs(int node)
	{
		if(level[node] < sqrtH)
			parentSection[node] = 1;
		else if(level[node] % sqrtH == 0)
			parentSection[node] = parentNode[node];
		else
			parentSection[node] = parentSection[parentNode[node]];
		
		for(int v : adjList[node])
			if(v != parentNode[node])
				dfs(v);
	}
	
	// query LCA in O(sqrt(H)) = O(sqrt(logN)) if the tree is balanced
	static int LCA_query(int u, int v)
	{
		while(parentSection[u] != parentSection[v])
			if(level[u] > level[v])
				u = parentSection[u];
			else
				v = parentSection[v];
		
		// Now they are in the same section
		while(u != v)
			if(level[u] > level[v])
				u = parentNode[u];
			else
				v = parentNode[v];
		return u;
	}
	
	
}
