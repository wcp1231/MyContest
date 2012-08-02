class POJ3814 {
	
	Scanner sc;
	PrintStream ps;
	int n;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		NetworkFlow_ISAP network;
		boolean[] willWork;
		n = sc.nextInt();
		while ( n != 0 ) {
			network = new NetworkFlow_ISAP( n+50, 98*n+100 );
			for ( int i = 1; i <= n; i++ ) {
				int k = sc.nextInt();
				int m = sc.nextInt();
				willWork = new boolean[ 1441 ];
				network.insert( 0, i, m/30 );
				for ( int j = 0; j < k; j++ ) {
					int st = getMinute( sc.next() );
					int et = getMinute( sc.next() );
					if ( et <= st ) et += 1440;
					for ( int t = st; t < et; t++ ) willWork[ t % 1440 ] = true;
				}
				for ( int t = 0; t < 48; t++ ) {
					boolean flag = true;
					for ( int tt = t*30; tt < (t+1)*30; tt++) if ( !willWork[ tt ] ) {
						flag = false;
						break;
					}
					if ( flag ) {
						network.insert( i, n+t+1, 1 );
					}
				}
			}
			int up = n+1, low = 0, mid;
			while ( low < up-1 ) {
				mid = ( low + up ) / 2;
				if ( network.getMaxFlow( 0, n+49, mid, n ) ) low = mid;
				else up = mid;
			}
			ps.println( low );
			n = sc.nextInt();
		}
	}
	public int getMinute( String time ) {
		String[] temp = time.split( ":" );
		int res = Integer.parseInt( temp[ 0 ] ) * 60 + Integer.parseInt( temp[ 1 ] );
		return res;
	}
}
class NetworkFlow_ISAP {
	int n, m, eid, source, sink, eid2;
	int[] p, h, gap, p2;
	Edge[] edge, e;
	public NetworkFlow_ISAP( int n, int m ) {
		this.n = n;
		this.m = m;
		edge = new Edge[ m ];
		p = new int[ n ];
		Arrays.fill( p, -1 );
		eid = 0;
	}
	public void insert( int from, int to, int val ) {
		edge[ eid ] = new Edge( to, p[ from ], val );
		p[ from ] = eid ++;
		
		edge[ eid ] = new Edge( from, p[ to ], 0 );
		p[ to ] = eid ++;
	}
	public void insert2( int from, int to, int val ) {
		e[ eid2 ] = new Edge( to, p2[ from ], val );
		p2[ from ] = eid2 ++;
		
		e[ eid2 ] = new Edge( from, p2[ to ], 0 );
		p2[ to ] = eid2 ++;
	}
	public int dfs( int pos, int cost ) {
		if ( pos == sink ) return cost;
		int minh = n - 1, lv = cost, d;
		for ( int j = p2[ pos ]; j != -1; j = e[ j ].next ) {
			int v = e[ j ].v, val = e[ j ].val;
			if ( val > 0 ) {
				if ( h[ v ] + 1 == h[ pos ] ) {
					d = Math.min( lv, e[ j ].val );
					
					d = dfs( v, d );
					e[ j ].val -= d;
					e[ j ^ 1 ].val += d;
					lv -= d;
					
					if ( h[ source ] >= n ) return cost - lv;
					if ( lv == 0 ) break;
				}
				if ( h[ v ] < minh ) minh = h[ v ];
			}
		}
		if ( lv == cost ) {
			--gap[ h[ pos ] ];
			if ( gap[ h[ pos ] ] == 0 ) h[ source ] = n;
			h[ pos ] = minh + 1;
			++gap[ h[ pos ] ];
		}
		return cost - lv;
	}
	public boolean getMaxFlow( int st, int ed, int x, int nn ) {
		e = new Edge[ eid+100 ];
		p2 = new int[ n ];
		for ( int i = 0; i < n; i++ ) p2[ i ] = p[ i ];
		for ( int i = 0; i < eid; i++ ) e[ i ] = new Edge( edge[ i ].v, edge[ i ].next, edge[ i ].val );
		eid2 = eid;
		for ( int i = 0; i < 48; i++ ) insert2( nn+i+1, nn+49, x );
		source = st;
		sink = ed;
		int res = 0;
		h = new int[ n ];
		gap = new int[ n+1 ];
		gap[ 0 ] = n;
		while ( h[ st ] < n ) {
			res += dfs( st, Integer.MAX_VALUE );
		}
		return res == x*48;
	}
}
class Edge {
	int v, next, val;
	public Edge( int v, int next, int val ) {
		this.v = v;
		this.next = next;
		this.val = val;
	}
}
