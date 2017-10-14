package library.dp;

import java.util.Arrays;

public class MaximumIncreasingSubsequence {
	
	// O(nlogn)
	public static void main(String[] args) {
		int a[] = {1,4,5,2,1,4,3,9,6,8};

		Pair[] a2 = new Pair[a.length];
		for(int i=0; i<a.length; i++)
			a2[i] = new Pair(a[i],i);

		Pair aClone[] = a2.clone();
		Arrays.sort(aClone);

		int rank[] = new int[a.length];

		for(int i=0; i<a.length; i++)
			rank[aClone[i].i] = i+1;

		int N = 1;
		while(N < a.length)
			N *= 2;
		int ans[] = new int[N+1];

		SegmentTree tree = new SegmentTree(ans);

		for(int i=0; i<a.length; i++)
		{
			if(rank[i] == 1)
				tree.update_point(1, a2[i].val);
			else
				tree.update_point(rank[i], tree.query(1, rank[i] - 1) + a2[i].val);
		}

		System.out.println(tree.query(1, N));



	}


	static class SegmentTree {		// 1-based DS, OOP

		int N; 			//the number of elements in the array as a multipls of 2 (i.e. after padding)
		int[] array, sTree;

		SegmentTree(int[] in)		
		{
			array = in; N = in.length - 1;
			sTree = new int[N<<1];		//no. of nodes = 2*N - 1, we add one to cross out index zero

			build(1,1,N);
		}

		void build(int node, int b, int e)	// O(n)
		{
			if(b == e)					
				sTree[node] = array[b];
			else						
			{
				build(node<<1,b,(b+e)/2);
				build((node<<1)+1,(b+e)/2+1,e);
				sTree[node] = Math.max(sTree[node<<1],sTree[(node<<1)+1]);
			}
		}


		void update_point(int index, int val)			// O(log n)
		{
			index += N - 1;				
			sTree[index] = val;			
			while(index>1)				
			{
				index >>= 1;
				sTree[index] = Math.max(sTree[index<<1], sTree[(index<<1) + 1]);		
			}
		}




		int query(int i, int j)
		{
			return query(1,1,N,i,j);
		}

		int query(int node, int b, int e, int i, int j)	// O(log n)
		{
			if(i>e || j <b)
				return 0;		
			if(b>= i && e <= j)
				return sTree[node];
			return Math.max(query(node<<1,b,(b+e)/2,i,j), query((node<<1)+1,(b+e)/2+1,e,i,j));	

		}

	}


	static class Pair implements Comparable<Pair>{
		int val, i;
		public Pair(int x, int y){ val = x; i = y; }

		public int compareTo(Pair p) {
			return val - p.val;
		}

	}
}
