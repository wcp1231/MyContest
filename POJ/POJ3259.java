class POJ3259 {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		Bellman bellman;
		int T = sc.nextInt();
		for ( int t = 0; t < T; t++ ) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			bellman = new Bellman( n, 2 * m + k );
			int con = 0;
			for ( int i = 0; i < m; i++ ) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int w = sc.nextInt();
				bellman.edge[ con++ ] = new Edge( s, e, w );
				bellman.edge[ con++ ] = new Edge( e, s, w );
			}
			for ( int i = 0; i < k; i++ ) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int w = sc.nextInt();
				bellman.edge[ con++ ] = new Edge( s, e, -w );
			}
			bellman.getShortestPath();
			if ( bellman.hasNegativeRing() ) ps.println( "YES" );
			else ps.println( "NO" );
		}
	}
}
class Bellman {
	int n, m;
	int[] dis;
	Edge[] edge;
	public Bellman( int n, int m ) {
		this.n = n + 1;
		this.m = m;
		edge = new Edge[ this.m ];
	}
	public void getShortestPath() {
		dis = new int[ n ];
		Arrays.fill( dis, Integer.MAX_VALUE );
		dis[ 1 ] = 0;
		for ( int i = 1; i < n; i++ ) {
			boolean flag = true;
			for ( int j = 0; j < m; j++ ) {
				int s = edge[ j ].s;
				int e = edge[ j ].e;
				int w = edge[ j ].w;
				if ( dis[ s ] != Integer.MAX_VALUE && dis[ s ] + w < dis[ e ] ) {
					dis[ e ] = dis[ s ] + w;
					flag = false;
				}
			}
			if ( flag ) break;
		}
	}
	public boolean hasNegativeRing() {
		for ( int i = 0; i < m; i++ ) {
			int s = edge[ i ].s;
			int e = edge[ i ].e;
			int w = edge[ i ].w;
			if ( dis[ s ] + w < dis[ e ] ) return true;
		}
		return false;
	}
}
class Edge {
	int s, e, w;
	public Edge( int s, int e, int w ) {
		this.s = s;
		this.e = e;
		this.w = w;
	}
}
