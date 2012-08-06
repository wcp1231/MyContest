// 树形背包DP 状态转移为
// dp[s][i] = max( dp[s][i], dp[s][i - 2*son.len - k] + dp[son][k] )
class ZOJ3626 {
	Scanner sc;
	PrintStream ps;
	
	int n, m, k, eid;
	Edge[] edge;
	int[] head, val;
	int[][] dp;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		while ( sc.hasNext() ) {
			n = sc.nextInt();
			val = new int[ n+5 ];
			head = new int[ n+5 ];
			edge = new Edge[ 2*n+5 ];
			Arrays.fill( head, -1 );
			eid = 0;
			for ( int i = 0; i < n; i++ ) val[ i ] = sc.nextInt();
			for ( int i = 1; i < n; i++ ) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int c = sc.nextInt();
				insertEdge( a, b, c );
				insertEdge( b, a, c );
			}
			k = sc.nextInt();
			m = sc.nextInt();
			dp = new int[ n+5 ][ m+5 ];
			TreeDP( k, -1 );
			int ans = 0;
			for ( int i = 0; i <= m; i++ ) ans = Math.max( ans, dp[ k ][ i ] );
			ps.println( ans );
		}
	}
	public void TreeDP( int s, int pa ) {
		for ( int p = head[ s ]; p != -1; p = edge[ p ].next ) {
			if ( edge[ p ].to == pa ) continue;
			TreeDP( edge[ p ].to, s );
			for ( int i = m; i >= 0; i-- ) {
				for ( int j = 0; j <= m; j++ ) if ( i >= (2 * edge[ p ].cost + j) ){
					dp[ s ][ i ] = Math.max( dp[ s ][ i ], dp[ s ][ i - 2 * edge[p].cost - j ] + dp[ edge[p].to ][ j ] );
				}
			}
		}
		for ( int i = 0; i <= m; i++ ) dp[ s ][ i ] += val[ s-1 ];
	}
	public void insertEdge( int from, int to, int cost ) {
		edge[ eid ] = new Edge( to, head[ from ], cost );
		head[ from ] = eid++;
	}
}
class Edge {
	int to, cost, next;
	public Edge( int to, int next, int cost ) {
		this.to = to;
		this.next = next;
		this.cost = cost;
	}
}
