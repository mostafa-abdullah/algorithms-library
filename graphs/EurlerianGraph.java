package library.graphs;

import java.util.ArrayList;

public class EurlerianGraph {
	static int N;
	static ArrayList<Pair> adjList[];
	
	
	static boolean hasEurlerianTour()
	{
		for(ArrayList l : adjList)
			if(l.size() % 2 == 1)
				return false;
		return true;
	}
	
	static boolean hasEurlerianPath()
	{
		int odd = 0;
		for(ArrayList l : adjList)
			if(l.size() % 2 == 1)
				odd++;
		return odd == 0 || odd == 2;
	}
	
	static boolean isEurlerian()
	{
		return hasEurlerianPath();
	}
	
	static ArrayList<Integer> tour;
	static void getEurlerianTour(int u)
	{
		for(Pair p : adjList[u])
			if(p.valid)
			{
				p.valid = false;
				for(Pair p2 : adjList[p.v])
				
					if(p2.valid && p2.v == u)
					{
						p2.valid = false;
						break;
					}
				tour.add(u);
				getEurlerianTour(p.v);
			}
	}
	
	static class Pair
	{
		int v;
		boolean valid;
		public Pair(int x, boolean y)
		{
			v = x;
			valid = y;
		}
	}
}
