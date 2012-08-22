/*
  原本 dp[ n ][ 1 << n ] 的写法可简化为 dp[ 1 << n ] 的写法
  复杂度从 n^3 * 2^n 简化为 n^2 * 2^n ，时间从 40多S 变成 3S
  之所以能这样简化，是因为状态的转移是单方向的，状态只会往大的方向转移
  所以每次遍历到的点都是之前状态的最优
  String[ 1 << n ] 的申请、操作的花费约为 3S 
 */
class HDU4385 {
	InputReader ir;
	PrintStream ps;
	int n;
	int[] x, y, dp, per;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out; //new PrintStream(new FileOutputStream(new File("test")));
		int T = ir.nextInt();
		Ans[] ans = new Ans[ 25 ];
		x = new int[ 21 ];
		y = new int[ 21 ];
		for ( int tt = 1; tt <= T; tt++ ) {
			x[ 0 ] = ir.nextInt();
			y[ 0 ] = ir.nextInt();
			n = ir.nextInt();
			n++;
			dp = new int[ 1 << n ];
			per = new int[ 1 << n ];
			Arrays.fill( dp, Integer.MAX_VALUE );
			for ( int i = 1; i < n; i++ ) {
				x[ i ] = ir.nextInt();
				y[ i ] = ir.nextInt();
			}
			dp[ 0 ] = 0;
			int len = 1 << n;
			for ( int mark = 0; mark < len; mark++ ) {
				for ( int j = 0; j < n-1; j++ ) if ( (mark & (1 << j)) == 0 ) {
					int dis = (x[0]-x[j+1])*(x[0]-x[j+1]) + (y[0]-y[j+1])*(y[0]-y[j+1]);
					int next = mark | (1 << j);
					if ( dp[next] > dp[mark]+(2*dis) ) {
						dp[ next ] = dp[ mark ] + (2 * dis);
						per[ next ] = mark;  // 从小往大遍历，答案相同的必然不会被覆盖
					}
					for ( int k = 0; k < n-1; k++ ) if ( j != k && (mark & (1 << k)) == 0 ) {
						dis += (x[j+1]-x[k+1])*(x[j+1]-x[k+1]) + (y[j+1]-y[k+1])*(y[j+1]-y[k+1]);
						int d = (x[0]-x[k+1])*(x[0]-x[k+1]) + (y[0]-y[k+1])*(y[0]-y[k+1]);
						next = mark | (1 << j) | (1 << k);
						if ( dp[next] > dp[mark]+dis+d ) {
							dp[ next ] = dp[ mark ] + dis + d;
							per[ next ] = mark;
						}
					}
				}
			}
			int mark = (1 << (n-1)) - 1;
			ps.println("Case " + tt + ":");
			ps.println( dp[ mark ] );
			int p = 0;
			while ( mark > 0 ) {
				int m = mark ^ per[ mark ];
				boolean flag = true;
				int a = 0, b = 25;
				for ( int i = 0; i < n-1; i++ ) if ( ((1<<i) & m) != 0 ) {
					if ( flag ) {
						a = i + 1;
						flag = false;
					} else b = i + 1;
				}
				ans[ p++ ] = new Ans( a, b );
				mark = per[ mark ];
			}
			Arrays.sort( ans, 0, p );
			for ( int i = 0; i < p; i++ ) {
				if ( i != 0 ) ps.print(" ");
				ps.print( ans[i].a );
				if ( ans[i].b < 25 ) ps.print(" " + ans[i].b);
			}
			ps.println();
		}
	}
}
class Ans implements Comparable<Ans> {
	int a, b;
	public Ans( int a, int b ) {
		this.a = a;
		this.b = b;
	}
	@Override
	public int compareTo(Ans o) {
		if ( a == o.a ) return b - o.b;
		return a - o.a;
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
