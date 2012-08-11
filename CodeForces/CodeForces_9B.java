class CodeForces_9B {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n = ir.nextInt();
		int vb = ir.nextInt();
		int vs = ir.nextInt();
		int[] x = new int[ n ];
		for ( int i = 0; i < n; i++ ) {
			x[ i ] = ir.nextInt();
		}
		int ux = ir.nextInt();
		int uy = ir.nextInt();
		int ans = 1;
		double time = Double.MAX_VALUE, t, dis = Double.MAX_VALUE;
		for ( int i = 1; i < n; i++ ) {
			double d = dis( x[ i ], ux, uy );
			t = d / vs + ( x[ i ] * 1.0 ) / vb;
			if ( t < time || ( t-time < 1e-9 && d < dis )) {
				time = t;
				ans = i + 1;
				dis = d;
			}
		}
		ps.println( ans );
	}
	public double dis( int p, int x, int y ) {
		return Math.sqrt( (x-p)*1.0*(x-p) + y*y );
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
