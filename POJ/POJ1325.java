// 二分图匹配
// 一个任务就是一条 A 到 B 的连线，求最大匹配
class POJ1325 {
	InputReader ir;
	PrintStream ps;
	int n, m;
	int[] match;
	boolean[] visted;
	boolean[][] map;
	public void doit() {
		ir = new InputReader( System.in );
		ps = System.out;
		n = ir.nextInt();
		while ( n != 0 ) {
			m = ir.nextInt();
			int k = ir.nextInt();
			match = new int[ n+m ];
			map = new boolean[ n+m ][ n+m ];
			for ( int i = 0; i < k; i++ ) {
				ir.nextInt();
				int a = ir.nextInt();
				int b = ir.nextInt();
				if ( a != 0 && b != 0 ) map[ a ][ n+b ] = map[ n+b ][ a ] = true;
			}
			ps.println( match() );
			n = ir.nextInt();
		}
	}
	public int match() {
		int ans = 0;
		for ( int i = 0; i < n+m; i++ ) {
			visted = new boolean[ n+m ];
			if ( dfs( i ) ) ans++;
		}
		return ans;
	}
	
	public boolean dfs(int n) {
		for (int i = 0; i < n; i++) {
			if ( map[ n ][ i ] && !visted[ i ]) {
				visted[ i ] = true;
				int temp = match[ i ];
				match[ i ] = n;
				if ( temp == 0 || dfs( temp ) ) return true;
				match[ i ] = temp;
			}
		}
		return false;
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
