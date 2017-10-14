package library.datastructures;

import java.util.Arrays;

public class UnionFind {
	
	int[] p,rank,setSize;
	int numSets;
	
	public UnionFind(int N)
	{
		p = new int[N];
		rank = new int[N];
		setSize = new int[N];
		
		for(int i=0; i<N; i++)
			p[i] = i;
		
		numSets = N;
		Arrays.fill(setSize, 1);
	}
	
	public int findSet(int i)
	{
		if(p[i] == i)
			return i;
		
		int root = findSet(p[i]);
		p[i] = root; // Path compression
		return root;
	}
	
	public boolean isSameSet(int i, int j)
	{
		return findSet(i) == findSet(j);
	}
	
	public void unionSet(int i, int j)
	{
		int x = findSet(i);
		int y = findSet(j);
		if(x == y)
			return;
		
		if(rank[x] > rank[y]){
			p[y] = x;
			setSize[x] += setSize[y];
		}
		else{
			p[x] = y;
			setSize[y] += setSize[x];
		}
		
		if(rank[x] == rank[y])
			rank[y]++;
		
		numSets--;
	}
	
	public int numSets()
	{
		return numSets;
	}
	
	public int setSize(int i)
	{
		return setSize[findSet(i)];
	}
	
}
