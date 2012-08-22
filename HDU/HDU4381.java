/*
  本质是简单的背包问题，两种操作可以分别看成是两个方向的背包。
  如： 1 ai xi 可以看成是从左往右，在 ai 容量下放一个体积为 xi 的物品
       2 ai xi 可以看成是从右往左，在 n-ai+1 容量下放一个体积为 xi 的物品
  最后答案就是占用的 最大容量（左边容量+右边容量） 和 使用的最少物品
 */
class HDU4381 {
	InputReader ir;
	PrintStream ps;
	int n, m;
	int[] dpl, dpr;
	Operator[] optl, optr;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out; //new PrintStream(new FileOutputStream(new File("test")));
		int T = ir.nextInt();
		for ( int tt = 1; tt <= T; tt++ ) {
			n = ir.nextInt();
			m = ir.nextInt();
			optl = new Operator[ m ];
			optr = new Operator[ m ];
			dpl = new int[ n+2 ];
			dpr = new int[ n+2 ];
			int l = 0, r = 0;
			for ( int i = 0; i < m; i++ ) {
				int type = ir.nextInt();
				int a = ir.nextInt();
				int x = ir.nextInt();
				if ( type == 1 ) optl[ l++ ] = new Operator( a, x );
				else optr[ r++ ] = new Operator( a, x );
			}
			Arrays.fill( dpl, 50000 );
			Arrays.sort( optl, 0, l );
			Arrays.fill( dpr, 50000 );
			Arrays.sort( optr, 0, r );
			dpl[ 0 ] = dpr[ n+1 ] = 0;
			for ( int i = 0; i < l; i++ ) {
				for ( int j = optl[i].a; j >= optl[i].x; j-- ) {
					dpl[ j ] = Math.min( dpl[ j ], dpl[ j-optl[i].x ]+1 );
				}
			}
			for ( int i = r-1; i >= 0; i-- ) {
				for ( int j = optr[i].a; j <= n-optr[i].x+1; j++ ) {
					dpr[ j ] = Math.min( dpr[ j ], dpr[ j+optr[i].x ]+1 );
				}
			}
			int max = 0, min = n+1;
			int ans = 0;
			boolean flag = true;
			for ( int i = 0; i <= n; i++ ) {
				if ( dpl[ i ] != 50000 ) max = i;
				if ( min == n+1 && dpr[ i ] != 50000 ) min = i;
				for ( int j = n+1; j > 0; j-- ) {
					if ( dpl[i] != 50000 && dpr[j] != 50000 && (i+n-j+1) <= n ) ans = Math.max( ans, i+n-j+1 );
				}
			}
			int t = max + n - min + 1;
			t = t > n ? 0 : t;
			ans = Math.max( ans, t );
			t = 50000;
			for ( int i = 1; i <= n; i++ ) {
				if ( i == ans ) t = Math.min( t, dpl[i] );
				if ( n-i+1 == ans ) t = Math.min( t, dpr[i] );
				int p = n - (ans-i) + 1;
				if ( dpl[i] < 50000 && dpr[p] < 50000 ) t = Math.min( t, dpl[i]+dpr[p] );
			}
			if ( ans == 0 || t == 50000 ) t = 0;
			ps.println( "Case " + tt + ": " + ans + " " + t );
		}
	}
}
class Operator implements Comparable<Operator> {
	int a, x;
	public Operator( int a, int x ) {
		this.a = a;
		this.x = x;
	}
	@Override
	public int compareTo(Operator o) {
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
