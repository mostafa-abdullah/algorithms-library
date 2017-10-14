package library.datastructures;

import java.util.Arrays;

public class FenwicTree {
	int [] arr;
	int [] tree;
	int N;
	
	public FenwicTree(int [] a)
	{
		N = a.length;
		arr = a;
		
		int [] tmp = new int[N+1];
		
		for(int i = 1; i<=N; i++)
		{
			tmp[i] = tmp[i-1] + a[i-1];
		}
		
		tree = new int[N+1];
		for(int i =1; i<= N; i++)
		{
			int lso = i & -i;
			tree[i] = tmp[i] - tmp[i - lso];
		}
	}
	
	public int rsq(int i)
	{
		int result = 0;
		while(i > 0) {
			result += tree[i];
			i -= (i & -1);
		}
		return result;
	}
	
	public int rsq(int l, int r)
	{
		return rsq(r) - rsq(l-1);
	}
	
	public void update(int i, int val)
	{
		while(i <= N)
		{
			tree[i] += val;
			int lso = i & -i;
			i += lso;
		}
	}
	
	public void update(int l, int r, int val)
	{
		update(l, val);
		update(r + 1, -val);
	}
	
	
	public static void main(String[] args) {
		int a[] = {1,4,3,2,5,7,8};
		
		FenwicTree t = new FenwicTree(a);
		
		System.out.println(Arrays.toString(t.tree));
		System.out.println(t.rsq(2,2));
		t.update(1,3, 2);
		System.out.println(t.rsq(2,2));
		System.out.println(Arrays.toString(t.tree));
		
	}
	
}
