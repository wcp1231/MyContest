class CodeForces_120A {
	
	Scanner sc;
	PrintStream ps;
	int n, m;
	int[] cost;
	boolean[] visted;
	boolean[][] match;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		n = sc.nextInt();
		m = sc.nextInt();
		cost = new int[ n+1 ];
		match = new boolean[ n+1 ][ n+1 ];
		for ( int i = 1; i <= n; i++ ) cost[ i ] = sc.nextInt();
		for ( int i = 0; i < m; i++ ) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			match[ a ][ b ] = match[ b ][ a ] = true;
		}
		int ans = Integer.MAX_VALUE;
		for ( int i = 1; i <= n; i++ ) {
			visted = new boolean[ n+1 ];
			visted[ i ] = true;
			ans = Math.min( ans, dfs( i, cost[ i ], 0 , i) );
		}
		if ( ans == Integer.MAX_VALUE ) ans = -1;
		ps.println( ans );
	}
	public int dfs( int p, int c, int d, int init ) {
		if ( d > 2 ) {
			return Integer.MAX_VALUE;
		}
		
		int res = Integer.MAX_VALUE;
		for ( int i = 1; i <= n; i++ ) {
			if ( !visted[ i ] && match[ p ][ i ] ) {
				visted[ i ] = true;
				res = Math.min( res, dfs( i, c+cost[ i ], d+1 , init) );
				visted[ i ] = false;
			} else if ( match[ p ][ i ] && d == 2 && i == init ) {
				return c;
			}
		}
		return res;
	}
}
