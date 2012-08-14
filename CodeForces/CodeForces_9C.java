/*
  题意是 1 到 n 中有多少个数只包含 1 和 0 两个字符
*/
class CodeForces_9C {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n = ir.nextInt();
		int ans = 0;
		for ( int i = 1; i < 1024; i++ ) {
			int temp = Integer.parseInt( Integer.toBinaryString(i) );
			if ( temp > n ) break;
			ans++;
		}
		ps.println( ans );
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
