package library.datastructures;

import java.util.Arrays;

public class MergeSort {
	static void sort(int a[])
	{
		sort(a, 0, a.length-1);
	}
	
	static void sort(int [] a, int b, int e)
	{
		if(b >= e)
			return;
		int mid = (b + e) / 2;
		sort(a, b, mid);
		sort(a, mid + 1, e);
		
		int tmp[] = new int[e - b + 1];
		int i = e;
		int j = mid;
		int k = tmp.length-1;
		while(k >= 0)
		{
			if(i == mid)
				tmp[k--] = a[j--];
			else if(j < b)
				tmp[k--] = a[i--];
			else {
				if(a[i] > a[j])
					tmp[k--] = a[i--];
				else
					tmp[k--] = a[j--];
			}
		}
		
		for(int l = b; l<=e; l++)
			a[l] = tmp[l - b];
	}
	
	public static void main(String[] args) {
		int a[] = {-1, 2, 7, 1, 6, -5, 4};
		
		sort(a);
		
		System.out.println(Arrays.toString(a));
	}
}
