// 最小路径覆盖， 点数 - 二分图最大匹配
class HDU3335 {
	InputReader ir;
	PrintStream ps;
	int n;
	int[] match;
	long[] num = new long[ 1005 ];
	boolean[] visted;
	boolean[][] map;
	public void doit() throws IOException {
		ir = new InputReader( System.in );
		ps = System.out;
		int T = ir.nextInt();
		for ( int t = 0; t < T; t++ ) {
			n = ir.nextInt();
			match = new int[ n ];
			map = new boolean[ n ][ n ];
			for ( int i = 0; i < n; i++ ) num[ i ] = ir.nextLong();
			Arrays.sort( num, 0, n );
			for ( int i = 1; i < n; i++ ) {
				for ( int j = i-1; j >= 0; j-- ) if ( num[ i ] % num[ j ] == 0 ) {
					map[ i ][ j ] = map[ j ][ i ] = true;
				}
			}
			ps.println( n - match() );
		}
	}
	public int match() {
		int ans = 0;
		for ( int i = 0; i < n; i++ ) {
			visted = new boolean[ n ];
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
