// 字符串 模拟
class HDU4329 {
	
	Scanner sc;
	PrintStream ps;
	int n;
	int count;
	String[][] r1, r2;
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int T = sc.nextInt();
		for ( int t = 1; t <= T; t++ ) {
			n = sc.nextInt();
			sc.nextLine();
			r1 = new String[ n ][];
			r2 = new String[ n ][];
			for ( int i = 0; i < n; i++ ) {
				r1[ i ] = sc.nextLine().split( "[ ]+" );
			}
			for ( int i = 0; i < n; i++ ) {
				r2[ i ] = sc.nextLine().split( "[ ]+" );
			}
			double ans = 0;
			for ( int i = 0; i < n; i++ ) {
				ans += AveP( i );
			}
			ps.printf( "Case #%d: %.6f", t, ans / n );
			ps.println();
		}
	}
	public boolean isCon( int k, int p ) {
		int len = r1[ k ].length;
		for ( int i = 1; i < len; i++ ) if ( r1[ k ][ i ].equals( r2[ k ][ p ] ) ) return true;
		return false;
	}
	public double AveP( int q ) {
		double res = 0;
		int len = r2[ q ].length;
		count = 0;
		for ( int i = 1; i < len; i++ ) {
			if ( isCon( q, i ) ) {
				count++;
				res += P( i );
			}
			
		}
		len = r1[ q ].length;
		return res / ( len-1 );
	}
	public double P( int r ) {
		double res = 0;
		res = ((double)count) / r;
		return res;
	}
}
