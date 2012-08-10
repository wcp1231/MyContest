class NetworkFlow_Dinic {
	
	int n, m;
	int S, T;
	int[] dis;
	int[][] cap;
	
	public NetworkFlow_Dinic( int n, int m ) {
		this.n = n;
		this.m = m;
		cap = new int[ n ][ m ];
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
		int flow;
		if ( u == T ) return f;
		for ( int i = 0; i <= T; i++ ) {
			if ( cap[ u ][ i ] > 0 && dis[ i ] == dis[ u ] + 1) {
				flow = dfs( i, Math.min( cap[ u ][ i ], f ) );
				if ( flow > 0 ) {
					cap[ u ][ i ] -= flow;
					cap[ i ][ u ] += flow;
					return flow;
				}
			}
		}
		return 0;
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
