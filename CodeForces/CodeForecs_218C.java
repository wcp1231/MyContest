class CodeForces_218C {
	InputReader ir;
	PrintStream ps;
	int n, m;
	int[] x, y;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		n = ir.nextInt();
		x = new int[ n ];
		y = new int[ n ];
		for ( int i = 0; i < n; i++ ) {
			x[i] = ir.nextInt();
			y[i] = ir.nextInt();
		}
		UnineFindSet set = new UnineFindSet( n );
		for ( int i = 0; i < n; i++ ) {
			for ( int j = i+1; j < n; j++ ) {
				if ( x[i] == x[j] || y[i] == y[j] ) set.unine( i, j );
			}
		}
		int m = 0;
		for ( int i = 0; i < n; i++ ) {
			if ( set.father[i] == i ) m++;
		}
		ps.println( m-1 );
	}
}
class UnineFindSet {
	int n;
	int[] father, rank;
	public UnineFindSet( int n ) {
		this.n = n;
		father = new int[ n ];
		rank = new int[ n ];
		for ( int i = 0; i < n; i++ ) {
			father[ i ] = i;
			rank[ i ] = 0;
		}
	}
	public int find( int x ) {
		if ( father[ x ] == x ) return x;
		int a = find( father[ x ] );
		return father[x] = a;
	}
	public void unine( int a, int b ) {
		int x = find( a );
		int y = find( b );
		if ( x != y ) {
			if ( rank[x] > rank[y] ) father[ y ] = x;
			else {
				father[x] = y;
				if ( rank[x] == rank[y] ) rank[y]++;
			}
		}
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
	public double nextDouble() {
		return Double.parseDouble( next() );
	}
}
