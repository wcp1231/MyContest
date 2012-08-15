/*
  组合数学 和 DP
  问 n 个节点高度至少为 h 的二叉树有多少种。
  大致思想是 一棵 n 个节点高 h 的二叉树的个数 为左右子树个数的乘积。乘法原理
*/
class CodeForces_9D {
	InputReader ir;
	PrintStream ps;
	int n, h;
	long[][] dp;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		n = ir.nextInt();
		h = ir.nextInt();
		dp = new long[ n+1 ][ n+1 ];
		dp[ 0 ][ 0 ] = 1;
		for ( int i = 1; i <= n; i++ ) {
			for ( int j = 1; j <= n; j++ ) {
				for ( int m = 1; m <= i; m++ ) {
					long temp = 0;
					for ( int h = 0; h < j; h++ ) {
						temp += dp[ i-m ][ h ];
					}
					dp[ i ][ j ] += dp[ m-1 ][ j-1 ] * temp;
					temp = 0;
					for ( int h = 0; h < j-1; h++ ) {
						temp += dp[ m-1 ][ h ];
					}
					dp[ i ][ j ] += dp[ i-m ][ j-1 ] * temp;
				}
			}
		}
		long ans = 0;
		for ( int i = h; i <= n; i++ ) ans += dp[ n ][ i ];
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
