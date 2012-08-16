class CodeForces_1B {
	InputReader ir;
	PrintStream ps;
	int n, h;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int T = ir.nextInt();
		for ( int t = 0; t < T; t++ ) {
			String inp = ir.next();
			String ans = "";
			if ( inp.matches( "R[\\d]+C[\\d]+" ) ) {
				int r = Integer.parseInt( inp.substring(1, inp.indexOf('C')) );
				int c = Integer.parseInt( inp.substring(inp.indexOf('C')+1) );
				String res = "";
				c--;
				while ( c >= 0 ) {
					res = ((char) (c % 26 + 'A')) + res;
					c /= 26;
					c--;
				}
				ans = res + r;
			} else {
				int r = 0, c = 0;
				int p = 0;
				while ( inp.charAt( p ) > '9' || inp.charAt( p ) < '0' ) {
					c = c * 26 + inp.charAt( p ) - 'A' + 1;
					p++;
				}
				r = Integer.parseInt( inp.substring( p ) );
				ans = "R" + r + "C" + c;
			}
			ps.println( ans );
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
}
