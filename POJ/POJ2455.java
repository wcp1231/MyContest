/*
  用 二分答案 + 网络流
  网络流用的是 Dinic 的多路增广，否则超时
  WA了几次的原因：1.是有重边，不能用邻接矩阵存边。
                 2.是二分条件写错，应该是流量 大于等于 k , 原本写的是等于。。。
*/
class POJ2455 {
	InputReader ir;
	PrintStream ps;
	int n, m, k;
	Edge[] edge;
	NetworkFlow_Dinic dinic;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		n = ir.nextInt();
		m = ir.nextInt();
		k = ir.nextInt();
		int eid = 0;
		edge = new Edge[ m ];
		dinic = new NetworkFlow_Dinic( n+2, n+2 );
		int low = Integer.MAX_VALUE, up = 0, mid = 0, ans = Integer.MAX_VALUE;
		for ( int i = 0; i < m; i++ ) {
			int a = ir.nextInt();
			int b = ir.nextInt();
			int c = ir.nextInt();
			edge[ eid++ ] = new Edge( a, b, c );
			if ( c > up ) up = c;
			if ( c < low ) low = c;
		}
		Arrays.sort( edge );
		while ( low <= up ) {
			mid = ( low + up ) / 2;
			initEdge( mid );
			if ( dinic.getMaxFlow( 1, n ) >= k ) {
				if ( ans > mid ) ans = mid;
				up = mid - 1;
			}
			else low = mid + 1;
		}
		ps.println( ans );
	}
	public void initEdge( int up ) {
		dinic.cap = new int[ n+2 ][ n+2 ];
		for ( int i = 0; i < m; i++ ) if ( edge[i].val <= up ) {
			dinic.cap[ edge[i].from ][ edge[i].to ]++;
			dinic.cap[ edge[i].to ][ edge[i].from ]++;
		}
	}
}
class NetworkFlow_Dinic {
	
	int n, m;
	int S, T;
	int[] dis;
	int[][] cap;
	
	public NetworkFlow_Dinic( int n, int m ) {
		this.n = n;
		this.m = m;
	}
	public int getMaxFlow( int s, int t ) {
		S = s;
		T = t;
		int res = 0;
		while ( bfs() ) {
			res += dfs( S, Integer.MAX_VALUE );
		}
		return res;
	}
	public int dfs( int u, int f ) {
		int flow, w = 0;
		if ( u == T ) return f;
		for ( int i = 0; i <= T && f-w > 0; i++ ) {
			if ( cap[ u ][ i ] > 0 && dis[ i ] == dis[ u ] + 1) {
				flow = dfs( i, Math.min( cap[ u ][ i ], f-w ) );
				if ( flow == 0 ) dis[ i ] = -1;
				else {
					w += flow;
					cap[ u ][ i ] -= flow;
					cap[ i ][ u ] += flow;
					//return flow;
				}
			}
		}
		return w;
	}
	public boolean bfs() {
		LinkedList<Integer> q = new LinkedList< Integer >();
		dis = new int[ n ];
		Arrays.fill( dis, -1 );
		dis[ S ] = 0;
		q.add( S );
		while ( !q.isEmpty() ) {
			int p = q.poll();
			for ( int i = 0; i <= T; i++ ) if ( dis[ i ] == -1 && cap[ p ][ i ] > 0 ) {
				dis[ i ] = dis[ p ] + 1;
				q.add( i );
			}
		}
		if ( dis[ T ] == -1 ) return false;
		return true;
	}
}

class Edge implements Comparable< Edge > {
	int from, to, val;
	public Edge( int from, int to, int val ) {
		this.to = to;
		this.from = from;
		this.val = val;
	}
	@Override
	public int compareTo(Edge o) {
		return val - o.val;
	}
}
class InputReader {
	private BufferedReader reader;
	private StringTokenizer tokenizer;
	
	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}
	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}
	public int nextInt() {
		return Integer.parseInt(next());
	}
	public long nextLong() {
		return Long.parseLong( next() );
	}
}
