/*
树形DP，dp[u][0] 表示      ，dp[u][1] 表示
状态转移：
1. u 为叶子
 u 为危险： dp[ u ][ 0 ] = INF, dp[ u ][ 1 ] = 0
 u 不危险： dp[ u ][ 0 ] = dp[ u ][ 1 ] = 0
2. u 不是叶子
 u 为危险
   dp[ u ][ 0 ] = INF
   dp[ u ][ 1 ] = sum( min( edge[u][s]+dp[s][1], dp[s][0] ) )
 u 不危险
   dp[ u ][ 0 ] = sum( min( edge[u][s]+dp[s][1], dp[s][0] ) )
   dp[ u ][ 1 ] = min( dp[u][0]-min(edge[u][s]+dp[s][1],dp[s][0])+dp[s][1] )
*/
class HDU4313 {
	InputReader ir;
	PrintStream ps;
	int n, m, eid;
	int[] head;
	long[][] dp;
	boolean[] isMachine;
	Edge[] edge;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int T = ir.nextInt();
		for ( int t = 0; t < T; t++ ) {
			n = ir.nextInt();
			m = ir.nextInt();
			eid = 0;
			head = new int[ n ];
			Arrays.fill( head, -1 );
			dp = new long[ n ][ 2 ];
			isMachine = new boolean[ n ];
			edge = new Edge[ 2*n ];
			for ( int i = 1; i < n; i++ ) {
				int a = ir.nextInt();
				int b = ir.nextInt();
				int c = ir.nextInt();
				insertEdge( a, b, c );
				insertEdge( b, a, c );
			}
			for ( int i = 0; i < m; i++ ) {
				int a = ir.nextInt();
				isMachine[ a ] = true;
			}
			treeDP( 0, -1 );
			ps.println( dp[ 0 ][ 1 ] );
		}
	}
	public void treeDP( int u, int f ) {
		int s = head[ u ];
		if ( edge[ s ].next == -1 && edge[ s ].to == f ) {
			dp[ u ][ 0 ] = dp[ u ][ 1 ] = 0;
			if ( isMachine[ u ] ) dp[ u ][ 0 ] = Long.MAX_VALUE;
			return ;
		}
		for ( ; s != -1; s = edge[ s ].next ) if ( edge[ s ].to != f ) treeDP( edge[ s ].to, u );
		for ( s = head[ u ]; s != -1; s = edge[ s ].next ) {
			int to = edge[ s ].to;
			if ( isMachine[ u ] ) {
				dp[ u ][ 0 ] = Long.MAX_VALUE;
				dp[ u ][ 1 ] += Math.min( edge[ s ].cost + dp[ to ][ 1 ], dp[ to ][ 0 ] );
			} else {
				dp[ u ][ 0 ] += Math.min( edge[ s ].cost + dp[ to ][ 1 ], dp[ to ][ 0 ] );
			}
		}
		if ( !isMachine[ u ] ) {
			dp[ u ][ 1 ] = Long.MAX_VALUE;
			for ( s = head[ u ]; s != -1; s = edge[ s ].next ) {
				int to = edge[ s ].to;
				dp[ u ][ 1 ] = Math.min( dp[ u ][ 0 ] - Math.min(edge[ s ].cost + dp[ to ][ 1 ], dp[ to ][ 0 ]) + dp[ to ][ 1 ], dp[ u ][ 1 ]);
			}
		}
	}
	public void insertEdge( int a, int b, int c ) {
		edge[ eid ] = new Edge( a, c, head[ b ] );
		head[ b ] = eid++;
	}
}
class Edge {
	int to, cost, next;
	public Edge( int to, int cost, int next ) {
		this.to = to;
		this.cost = cost;
		this.next = next;
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
