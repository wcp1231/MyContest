class CodeForces_218A {
	InputReader ir;
	PrintStream ps;
	int n, k;
	int[] mo;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		n = ir.nextInt();
		k = ir.nextInt();
		int len = 2 * n + 1;
		mo = new int[ len ];
		for ( int i = 0; i < len; i++ ) {
			mo[ i ] = ir.nextInt();
		}
		int kk = 0;
		for ( int i = 1; i < len-1; i++ ) if ( mo[i-1]+1 < mo[i] && mo[i+1]+1 < mo[i] ) {
			mo[i]--;
			kk++;
			if ( kk == k ) break;
		}
		for ( int i = 0; i < len; i++ ) {
			if ( i != 0 ) ps.print( " " );
			ps.print( mo[i] );
		}
		ps.println();
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
