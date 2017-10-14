package library.datastructures;

public class InversionIndex {
public static void main(String[] args) {
		
		int a[] = {7,6,8,3,1,4,2,5};
		
		int indx = inversionIndex(a);
		
		System.out.println(indx);
	}
	
	
	static int inversionIndex(int [] a) {
		return inversionIndex(a, 0, a.length - 1);
	}
	
	static int inversionIndex(int [] a, int b, int e) {
		if(b == e)
			return 0;
		
		int res = 0;
		
		res += inversionIndex(a, b, (b + e) / 2);
		res += inversionIndex(a, (b + e) / 2 + 1, e);
		
		int tmp[] = new int[e - b + 1];
		int i = e;
		int j = (b + e) / 2;
		int k = tmp.length - 1;
		
		while(i > (b + e) / 2 && j >= b) {
			if(a[j] > a[i]) {
				tmp[k--] = a[j--];
				res += i - (b + e) / 2;
			}
			else {
				tmp[k--] = a[i--];
			}
		}
		
		while(i > (b + e) / 2) {
			tmp[k--] = a[i--];
		}
		
		while(j >= b) {
			tmp[k--] = a[j--];
		}
		
		for(i = b; i<=e; i++)
			a[i] = tmp[i - b];
		
		return res;
	}
}
