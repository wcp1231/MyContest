class POJ2240 {
	
	Scanner sc;
	PrintStream ps;
	int n;
	String[] currency;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		Bellman bellman;
		n = sc.nextInt();
		int T = 1;
		while ( n != 0 ) {
			currency = new String[ n ];
			for ( int i = 0; i < n; i++ ) currency[ i ] = sc.next();
			int m = sc.nextInt();
			bellman = new Bellman( n, m );
			for ( int i = 0; i < m; i++ ) {
				String s = sc.next();
				double rate = sc.nextDouble();
				String e = sc.next();
				int from = getCurrencyIndex( s );
				int to = getCurrencyIndex( e );
				bellman.edge[ i ] = new Edge( from, to, rate );
			}
			bellman.getShortestPath();
			ps.print( "Case " + T + ": " );
			T++;
			if ( bellman.hasNegativeRing() ) ps.println("Yes");
			else ps.println( "No" );
			n = sc.nextInt();
		}
	}
	public int getCurrencyIndex( String curr ) {
		for ( int i = 0; i < n; i++ ) if ( currency[ i ].equals( curr ) ) return i;
		return -1;
	}
}
class Bellman {
	int n, m;
	double[] dis;
	Edge[] edge;
	public Bellman( int n, int m ) {
		this.n = n + 1;
		this.m = m;
		edge = new Edge[ this.m ];
	}
	public void getShortestPath() {
		dis = new double[ n ];
		Arrays.fill( dis, -1 );
		dis[ 0 ] = 1;
		for ( int i = 1; i < n; i++ ) {
			boolean flag = true;
			for ( int j = 0; j < m; j++ ) {
				int s = edge[ j ].s;
				int e = edge[ j ].e;
				double w = edge[ j ].w;
				Double temp = new Double( dis[ e ] );
				if ( dis[ s ] != -1 && temp.compareTo( new Double( dis[s]*w ) ) < 0 ) {
					dis[ e ] = dis[ s ] * w;
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
			double w = edge[ i ].w;
			Double temp = new Double( dis[ e ] );
			if ( temp.compareTo( new Double ( dis[ s ] * w ) ) < 0 ) return true;
		}
		return false;
	}
}
class Edge {
	int s, e;
	double w;
	public Edge( int s, int e, double w ) {
		this.s = s;
		this.e = e;
		this.w = w;
	}
}
