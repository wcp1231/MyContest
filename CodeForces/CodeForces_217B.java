class CodeForces_217B {
	InputReader ir;
	PrintStream ps;
	int n, r;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		n = ir.nextInt();
		r = ir.nextInt();
		int minError = Integer.MAX_VALUE, minQ = 0;
		for ( int q = 1; q <= r; q++ ) {
			int a = r;
			int b = q;
			int error = 0, time = 0;
			while ( b > 0 ) {
				int whole = a / b;
				error += whole - 1;
				time += whole;
				int t = a - whole * b;
				a = b;
				b = t;
			}
			if ( time == n && a == 1) {
				if ( error < minError ) {
					minError = error;
					minQ = q;
				}
			}
		}
		if ( minError == Integer.MAX_VALUE ) ps.println("IMPOSSIBLE");
		else {
			if ( minError == 0 ) minError = 1;
			ps.println( minError-1 );
			boolean f = false;
			boolean[] flag = new boolean[ n ];
			int a = r;
			int b = minQ;
			int p = n - 1;
			while ( b > 0 ) {
				int whole = a / b;
				int t = a - whole * b;
				while ( whole > 0 ) {
					flag[ p-- ] = f;
					whole--;
				}
				f = f ? false : true;
				a = b;
				b = t;
			}
			ps.println();
			flag[ 0 ] = flag[ 0 ] ? false : true;   // 这样使得总错误次数减少一？
			for ( int i = 0; i < n; i++ ) {
				ps.print( flag[i] == flag[0] ? "T" : "B" );
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
