class CodeForces_9A {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int a = ir.nextInt();
		a = Math.max( a, ir.nextInt() );
		a = 7 - a;
		if ( a == 6 ) ps.println( "1/1" );
		else if ( a % 2 == 0 ) ps.println( (a/2) + "/" + 3 );
		else if ( a == 3 ) ps.println( "1/2" );
		else ps.println( a + "/6" );
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
