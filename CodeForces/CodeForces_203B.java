class CodeForces_203B {
	
	Scanner sc;
	PrintStream ps;
	boolean[][] black;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		int m = sc.nextInt();
		black = new boolean[ n+1 ][ n+1 ];
		int ans = -1;
		boolean flag = false;
		for ( int i = 1; i <= m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			black[ x ][ y ] = true;
			int sx = Math.max( 1, x-2 );
			int ex = Math.min( n, x+2 );
			int sy = Math.max( 1, y-2 );
			int ey = Math.min( n, y+2 );
			for ( int xx = sx+1; xx < ex; xx++ ) {
				for ( int yy = sy+1; yy < ey; yy++ ) {
					if ( check( xx, yy ) ) {
						ans = i;
						flag = true;
						break;
					}
				}
				if ( flag ) break;
			}
			if ( flag ) break;
		}
		ps.println( ans );
	}
	public boolean check( int x, int y ) {
		return black[ x-1 ][ y-1 ] && black[ x-1 ][ y ] && black[ x-1 ][ y+1 ] && black[ x ][ y-1 ] && black[ x ][ y ] && black[ x ][ y+1 ] && black[ x+1 ][ y-1 ] && black[ x+1 ][ y ] && black[ x+1 ][ y+1 ];
	}
}
