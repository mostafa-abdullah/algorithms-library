package library.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import library.datastructures.UnionFind;

public class MST {
	
	static ArrayList<Edge> edgeList;
	static int N;
	static int Kruskal()
	{
		Collections.sort(edgeList);
		
		int mst = 0;
		UnionFind uf = new UnionFind(N);
		
		for(Edge e : edgeList)
			if(!uf.isSameSet(e.u, e.v))
			{
				mst += e.w;
				uf.unionSet(e.u, e.v);
			}
		
		return mst;
	}
	
	
	
	static class Edge implements Comparable<Edge>
	{
		int u,v,w;
		public Edge(int x, int y, int z)
		{
			u = x;
			v = y;
			w = z;
		}
		public int compareTo(Edge e) {
			return w - e.w;
		}
		
	}
	
	
	static ArrayList<Pair> adjList[];
	static int Prim()
	{
		int mst = 0;
		boolean taken[] = new boolean[N];
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		taken[0] = true;
		for(Pair p : adjList[0])
			if(!taken[p.u])
				q.add(p);
		
		while(!q.isEmpty())
		{
			Pair p = q.remove();
			if(!taken[p.u])
			{
				mst += p.w;
				for(Pair ps : adjList[p.u])
					if(!taken[ps.u])
						q.add(ps);
			}
			
			
		}
		return mst;
	}
	
	
	
	static class Pair implements Comparable<Pair>{
		int u, w;
		public Pair(int x, int y) {
			u = x; w = y;
		} 
		public int compareTo(Pair p) {
			if(w == p.w)
				return u - p.u;
			return w - p.w;
		}
		
	}
	
	
}