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
	public void insert( int from, int to, int val ) {
		edge[ eid ].v = to;
		edge[ eid ].val = val;
		edge[ eid ].next = p[ from ];
		p[ from ] = eid ++;
		
		edge[ eid ].v = from;
		edge[ eid ].val = 0;
		edge[ eid ].next = p[ to ];
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
		gap = new int[ n ];
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
