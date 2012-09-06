class CodeForces_4B {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n = ir.nextInt();
		int st = ir.nextInt();
		int[] mint = new int[ n ], maxt = new int[ n ], day = new int[ n ];
		int minT = 0, maxT = 0;
		for ( int i = 0; i < n; i++ ) {
			mint[ i ] = ir.nextInt();
			maxt[ i ] = ir.nextInt();
			minT += mint[ i ];
			maxT += maxt[ i ];
		}
		if ( maxT < st || st < minT ) ps.println( "NO" );
		else {
			ps.println( "YES" );
			for ( int i = 0; i < n; i++ ) {
				day[ i ] = mint[ i ];
				st -= mint[ i ];
			}
			int p = 0;
			while ( st > 0 ) {
				int t = maxt[ p ] - mint[ p ];
				if ( t < st ) {
					day[ p ] += t;
					st -= t;
					p++;
				} else {
					day[ p ] += st;
					st = 0;
				}
			}
			for ( int i = 0; i < n; i++ ) {
				if ( i != 0 ) ps.print( " " );
				ps.print( day[i] );
			}
			ps.println();
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
