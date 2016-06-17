package library.graphs;

import java.util.ArrayList;

public class EurlerianGraph {
	static int N;
	static ArrayList<Integer> adjList[];
	
	
	static boolean hasEurlerianTour()
	{
		for(ArrayList<Integer> l : adjList)
			if(l.size() % 2 == 1)
				return false;
		return true;
	}
	
	static boolean hasEurlerianPath()
	{
		int odd = 0;
		for(ArrayList<Integer> l : adjList)
			if(l.size() % 2 == 1)
				odd++;
		return odd == 0 || odd == 2;
	}
	
	static boolean isEurlerian()
	{
		return hasEurlerianPath();
	}
}
