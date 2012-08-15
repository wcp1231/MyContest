/*
  java 超时版
*/
class HDU4364 {
	InputReader ir;
	PrintStream ps;
	int[][] mat, ans, AES = {{2, 3, 1, 1},{1, 2, 3, 1}, {1, 1, 2, 3}, {3, 1, 1, 2}};
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int T = ir.nextInt();
		mat = new int[ 4 ][ 4 ];
		ans = new int[ 4 ][ 4 ];
		for ( int t = 0; t < T; t++ ) {
			if ( t != 0 ) ps.println();
			for ( int i = 0; i < 4; i++ ) for ( int j = 0; j < 4; j++ ) {
				mat[ i ][ j ] = Integer.parseInt( ir.next(), 16 );
			}
			for ( int i = 0; i < 4; i++ ) for ( int j = 0; j < 4; j++ ) {
				ans[ i ][ j ] = opt( i, j );
			}
			for ( int i = 0; i < 4; i++ ) {
				for ( int j = 0; j < 4; j++ ) {
					if ( j != 0 ) ps.print( " " );
					ps.printf( "%02X", ans[ i ][ j ] );
				}
				ps.println();
			}
		}
	}
	public int opt( int x, int y ) {
		int res = 0;
		for ( int i = 0; i < 4; i++ ) {
			int o = AES[ x ][ i ], temp = 0;
			if ( o == 1 ) {
				temp = mat[ i ][ y ];
			} else if ( o == 2 ) {
				temp = mat[ i ][ y ] << 1;
				if ( temp > 0xFF ) temp ^= 0x1B;
			} else if ( o == 3 ) {
				temp = mat[ i ][ y ] << 1;
				if ( temp > 0xFF ) temp ^= 0x1B;
				temp ^= mat[ i ][ y ];
			}
			res ^= temp;
		}
		return res;
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
