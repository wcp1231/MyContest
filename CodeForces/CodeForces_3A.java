class CodeForces_3A {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		String sp = ir.next();
		String ep = ir.next();
		int y = Integer.parseInt( sp.substring(1, 2) ) - Integer.parseInt( ep.substring(1, 2) );
		int x = sp.charAt(0) - ep.charAt(0);
		ps.println( Math.max( Math.abs(x), Math.abs(y)) );
		while ( x != 0 && y != 0 ) {
			String res = "";
			if ( x > 0 ) {res += "L"; x--;}
			else if ( x < 0 ) {res += "R"; x++;}
			if ( y > 0 ) {res += "D"; y--;}
			else if ( y < 0 ) {res += "U"; y++;}
			ps.println( res );
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
