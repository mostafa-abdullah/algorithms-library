package library.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NetworkFlow {

	static int N;
	static int adjMat[][];
	static ArrayList<Integer> adjList[];
	static int p[]; // parent array from BFS tree
	static int s,t;
	
	static int augment(int v, int minEdge)
	{
		if(v == s)
			return minEdge;
		if(p[v] != -1)
		{
			int f = augment(p[v], Math.min(minEdge, adjMat[p[v]][v]));
			adjMat[p[v]][v] -= f;
			adjMat[v][p[v]] += f;
			return f;
		}
		return 0;
	}
	
	static int EdmondKarp()
	{
		int mf = 0;
		while(true)
		{
			boolean visited[] = new boolean[N];
			p = new int[N];
			
			Arrays.fill(p, -1);
			visited[s] = true;
			
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(s);
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == t)
					break;
				
				for(int v : adjList[u])
					if(!visited[v] && adjMat[u][v] > 0)
					{
						visited[v] = true;
						p[v] = u;
						q.add(v);
					}
			}
			
			int f = augment(t, (int)1e9);
			if(f == 0)
				break;
			mf += f;
			
		}
		return mf;
	}
	
	static int mincut()
	{
		return EdmondKarp();
	}
	
}
