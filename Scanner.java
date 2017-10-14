package library; 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Scanner {
	BufferedReader br;
	StringTokenizer st;

	Scanner(FileReader f) {
		br = new BufferedReader(f);
	}

	public boolean ready() throws IOException {
		return br.ready();
	}

	Scanner(InputStream s) {
		br = new BufferedReader(new InputStreamReader(s));
	}

	String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}

	String nextLine() throws IOException {
		return br.readLine();
	}

	int nextInt() throws NumberFormatException, IOException {
		return Integer.parseInt(next());
	}

	long nextLong() throws NumberFormatException, IOException {
		return Long.parseLong(next());
	}
}