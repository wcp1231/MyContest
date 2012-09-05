/*
  一开始我用 HashSet 怎样都过不了 Test7 ,之后发现用 HashMap 居然就几百毫秒过了
  这。。难道是因为用 set 把每个 用户名+数字 都存了所有查询效率太低？
 */
class CodeForces_4C {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int n = ir.nextInt();
		StringBuffer ans = new StringBuffer();
		for ( int i = 0; i < n; i++ ) {
			String usr = ir.next();
			if ( !map.containsKey( usr ) ) {
				ans.append("OK\n");
				map.put( usr, 1 );
			} else {
				int v = map.get( usr );
				ans.append( usr+v );
				ans.append( "\n" );
				map.put( usr, v+1 );
			}
		}
		ps.println( ans.toString() );
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
