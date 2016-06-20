package library.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SSSP {
	static int N;
	static ArrayList<Pair> adjList[];
	
	
	// O((V+E)log(V))
	static int[] dijkstra(int s)
	{
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		int dist[] = new int[N];
		Arrays.fill(dist, (int)1e9);
		dist[s] = 0;
		
		q.add(new Pair(s,0));
		while(!q.isEmpty())
		{
			Pair p = q.remove();
			if(p.w > dist[p.v])
				continue;
			
			for(Pair v : adjList[p.v])
				if(dist[p.v] + v.w < dist[v.v])
				{
					dist[v.v] = dist[p.v] + v.w;
					q.add(new Pair(v.v, dist[v.v]));
				}
			
		}
		return dist;
		
	}
	
	
	
	//O(VE)
	static int[] bellmanFord(int s)
	{
		int dist[] = new int[N];
		Arrays.fill(dist, (int)1e9);
		dist[0] = 0;
		for(int i = 0; i < N - 1; i++)
			for(int u = 0; u < N; u++)
				for(Pair p : adjList[u])
					dist[p.v] = Math.min(dist[p.v], dist[u] + p.w);
		
		return dist;
		
	}
	
	
	
	static boolean hasNegativeCycles()
	{
		int dist[] = bellmanFord(0);
		for(int u = 0; u < N; u++)
			for(Pair p : adjList[u])
				if(dist[u] + p.w < dist[p.v])
					return true;
		return false;
		
	}
	
	
	
	static class Pair implements Comparable<Pair>{
		int v,w;
		public Pair(int x, int y)
		{
			v = x;
			w = y;
		}
		
		public int compareTo(Pair p) {
			return w - p.w;
		}
		
		
	}
	
}
