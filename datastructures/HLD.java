import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by mostafa on 10/14/17.
 */
public class HLD {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner();
        n = sc.nextInt();
        adj = new ArrayList[n];
        for(int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for(int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
            adj[u].add(v); adj[v].add(u);
        }
        val = new int[n];
        pre();

        int q = sc.nextInt();

        PrintWriter out = new PrintWriter(System.out);
        while(q-- > 0) {
            char t = sc.next().charAt(0);
            if(t == 'I')
                update(sc.nextInt() - 1, sc.nextInt());
            else
                out.println(query(sc.nextInt() - 1, sc.nextInt() - 1));
        }

        out.flush();
        out.close();
    }

    static class Scanner {
        BufferedReader br;
        StringTokenizer st;
        Scanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        Scanner(String f) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(f));
        }

        String next() throws IOException {
            while(st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    static ArrayList<Integer>[] adj;
    static int[] val;
    static SegmentTree st;
    static int[] size, level, chainHeads, posInChain, posInSt, chainIdx, chainSize, P[];
    static int n, chainNo, hldNum;

    static void hld(int u) {
        if(chainHeads[chainNo] == -1)
            chainHeads[chainNo] = u;
        chainIdx[u] = chainNo;
        posInChain[u] = chainSize[chainNo]++;
        posInSt[u] = ++hldNum;
        int spCh = -1;
        int max = -1;
        for(int v: adj[u])
            if(size[v] < size[u] && size[v] > max) {
                max = size[v];
                spCh = v;
            }

        if(spCh == -1)
            return;
        hld(spCh);
        for(int v: adj[u])
            if(size[v] < size[u] && v != spCh) {
                chainNo++;
                hld(v);
            }
    }

    static void pre() {
        int tmp = n, k = 0;
        while(tmp > 0) {
            k++;
            tmp /= 2;
        }
        P = new int[n][k];
        for(int i = 0; i < n; i++)
            Arrays.fill(P[i], -1);
        size = new int[n];
        level = new int[n];
        dfs(0, -1, 0);
        for(int j = 1; j < k; j++)
            for(int i = 0; i < n; i++)
                if(P[i][j - 1] != -1)
                    P[i][j] = P[P[i][j - 1]][j - 1];

        chainHeads = new int[n];
        posInChain = new int[n];
        posInSt = new int[n];
        chainIdx = new int[n];
        chainSize = new int[n];

        Arrays.fill(chainHeads, -1);
        hld(0);

        int N = 1;
        while(N <= n)
            N *= 2;
        int[] in = new int[N + 1];
        for(int i = 0; i < n; i++)
            in[posInSt[i]] = val[i];
        st = new SegmentTree(in);
    }

    static void dfs(int u, int p, int l) {
        level[u] = l;
        size[u] = 1;
        for(int v: adj[u])
            if(v != p) {
                P[v][0] = u;
                dfs(v, u, l + 1);
                size[u] += size[v];
            }
    }

    static int lca(int u, int v) {
        if(level[u] < level[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        int log;
        for(log = 1; 1 << log <= level[u]; log++); log--;
        for(int i = log; i >= 0; i--)
            if(level[u] - (1 << i) >= level[v])
                u = P[u][i];
        if(u == v)
            return u;
        for(int i = log; i >= 0; i--)
            if(P[u][i] != -1 && P[u][i] != P[v][i]) {
                u = P[u][i]; v = P[v][i];
            }
        return P[u][0];
    }

    static void update(int u, int value) {
        st.update(posInSt[u], value);
        val[u] += value;
    }

    static int query(int u, int v) {
        int lca = lca(u, v);
        return Math.max(crawl(u, lca), crawl(v, lca));
    }

    static int crawl(int u, int v) {
        int uChain = chainIdx[u], vChain = chainIdx[v], ans = 0;
        while(uChain != vChain) {
            ans = Math.max(ans, st.query(posInSt[chainHeads[uChain]], posInSt[u]));
            u = P[chainHeads[uChain]][0];
            uChain = chainIdx[u];
        }

        ans = Math.max(ans, st.query(posInSt[v], posInSt[u]));
        return ans;
    }

    static class SegmentTree {
        int[] tree, lazy, arr;
        int N;
        SegmentTree(int N) {
            this.N = N;
            tree = new int[N << 1];
            lazy = new int[N << 1];
        }
        SegmentTree(int[] in) {
            this(in.length - 1);
            this.arr = in;
            build(1, 1, N);
        }
        void build(int node, int b, int e) {
            if(b == e) {
                tree[node] = arr[b];
                return;
            }
            int mid = (b + e) / 2;
            build(node << 1, b, mid);
            build((node << 1) + 1, mid + 1, e);
            tree[node] = Math.max(tree[node << 1], tree[(node << 1) + 1]);
        }

        void update(int idx, int val) {
            idx += N - 1;
            tree[idx] += val;
            while(idx > 1) {
                idx >>= 1;
                tree[idx] = Math.max(tree[idx << 1], tree[(idx << 1) + 1]);
            }
        }

        void update(int l, int r, int val) {
            update(1, 1, N, l, r, val);
        }

        void update(int node, int b, int e, int l, int r, int val) {
            if(b > r || e < l)
                return;
            if(b >= l && e <= r) {
                tree[node] += val;
                lazy[node] += val;
                return;
            }

            propagate(node);
            int mid = (b + e) / 2;
            update(node << 1, b, mid, l, r, val);
            update((node << 1) + 1, mid + 1, e, l, r, val);
            tree[node] = Math.max(tree[node << 1], tree[(node << 1) + 1]);
        }

        void propagate(int node) {
            lazy[node << 1] += lazy[node];
            lazy[(node << 1) + 1] += lazy[node];
            tree[node << 1] += lazy[node];
            tree[(node << 1) + 1] += lazy[node];
            lazy[node] = 0;
        }

        int query(int l, int r) {
            return query(1, 1, N, l, r);
        }

        int query(int node, int b, int e, int l, int r) {
            if(b > r || e < l)
                return 0;
            if(b >= l && e <= r)
                return tree[node];
            propagate(node);
            int mid = (b + e) / 2;
            return Math.max(query(node << 1, b, mid, l, r), query((node << 1) + 1, mid + 1, e, l, r));
        }
    }
}

