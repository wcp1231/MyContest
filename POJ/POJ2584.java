/*
  简单网络流
*/
class POJ2584 {
	InputReader ir;
	PrintStream ps;
	int n, m;
	NetworkFlow_ISAP isap;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		String isEnd = ir.next();
		while ( !isEnd.equals( "ENDOFINPUT" ) ) {
			int num = ir.nextInt();
			n = num + 5 + 2;
			m = (num + 1) * 12;
			isap = new NetworkFlow_ISAP( n, m );
			for ( int i = 1; i <= num; i++ ) {
				isap.insert1( 0, i, 1 );
				String ts = ir.next();
				int s = getIndex( ts.charAt( 0 ) );
				int e = getIndex( ts.charAt( 1 ) );
				for ( int j = s; j <= e; j++ ) isap.insert1( i, j+num+1, 1 );
			}
			for ( int i = 1; i < 6; i++ ) {
				int c = ir.nextInt();
				isap.insert1( num+i, n-1, c );
			}
			ir.next();
			int res = isap.getMaxFlow( 0, n-1 );
			ps.println( res == num ? "T-shirts rock!" : "I'd rather not wear a shirt anyway..." );
			isEnd = ir.next();
		}
	}
	public int getIndex( char ch ) {
		if ( ch == 'S' ) return 0;
		if ( ch == 'M' ) return 1;
		if ( ch == 'L' ) return 2;
		if ( ch == 'X' ) return 3;
		if ( ch == 'T' ) return 4;
		return -1;
	}
}
class NetworkFlow_ISAP {
	int n, m, eid, source, sink;
	int[] p, h, gap;
	Edge[] edge;
	public NetworkFlow_ISAP( int n, int m ) {
		this.n = n;
		this.m = m;
		edge = new Edge[ m ];
		p = new int[ n ];
		Arrays.fill( p, -1 );
		eid = 0;
	}
	public void insert1( int from, int to, int val ) {
		edge[ eid ] = new Edge( to, p[ from ], val );
		p[ from ] = eid ++;
		
		edge[ eid ] = new Edge( from, p[ to ], 0 );
		p[ to ] = eid ++;
	}
	public void insert2( int from, int to, int val ) {
		edge[ eid ] = new Edge( to, p[ from ], val );
		p[ from ] = eid ++;
		
		edge[ eid ] = new Edge( from, p[ to ], val );
		p[ to ] = eid ++;
	}
	public int dfs( int pos, int cost ) {
		if ( pos == sink ) return cost;
		int minh = n - 1, lv = cost, d;
		for ( int j = p[ pos ]; j != -1; j = edge[ j ].next ) {
			int v = edge[ j ].v, val = edge[ j ].val;
			if ( val > 0 ) {
				if ( h[ v ] + 1 == h[ pos ] ) {
					d = Math.min( lv, edge[ j ].val );
					
					d = dfs( v, d );
					edge[ j ].val -= d;
					edge[ j ^ 1 ].val += d;
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
	public int getMaxFlow( int st, int ed  ) {
		source = st;
		sink = ed;
		int res = 0;
		h = new int[ n ];
		gap = new int[ n+1 ];
		gap[ 0 ] = n;
		while ( h[ st ] < n ) {
			res += dfs( st, Integer.MAX_VALUE );
		}
		return res;
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
