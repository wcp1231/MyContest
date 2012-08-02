class ZOJ3471 {
	Scanner sc;
	PrintStream ps;
	int[][] atoms, dp;
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		while ( n != 0 ) {
			int len = 1 << n;
			atoms = new int[ n ][ n ];
			dp = new int[ n+1 ][ len ];
			for ( int i = 0; i < n; i++ ) {
				for ( int j = 0;j < n; j++ ) atoms[ i ][ j ] = sc.nextInt();
				Arrays.fill( dp[ i ], -1 );
			}
			dp[ 0 ][ 0 ] = 0;
			for ( int i = 1; i <= n; i++ ) {
				for ( int mark = 0; mark < len; mark++ ) if ( dp[ i-1 ][ mark ] != -1 ){
					for ( int j = 0; j < n; j++ ) if ( (mark & (1 << j)) == 0 ) {
						for ( int k = j+1; k < n; k++ ) if ( k != j && (mark & (1 << k)) == 0 ) {
							int next = mark | ( 1 << j );
							dp[ i ][ next ] = Math.max( dp[ i ][ next ], dp[ i-1 ][ mark ]+atoms[ k ][ j ]);
							next = mark | ( 1 << k );
							dp[ i ][ next ] = Math.max( dp[ i ][ next ], dp[ i-1 ][ mark ]+atoms[ j ][ k ]);
						}
					}
				}
			}
			int ans = 0;
			for ( int i = 0; i < len; i++ ) ans = Math.max( ans, dp[ n-1 ][ i ] );
			ps.println( ans );
			n = sc.nextInt();
		}
	}
}
