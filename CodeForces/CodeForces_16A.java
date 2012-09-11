class CodeForces_16A {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n, m;
		n = ir.nextInt();
		m = ir.nextInt();
		char[][] flag = new char[ n ][ m ];
		for ( int i = 0; i < n; i++ ) flag[ i ] = ir.next().toCharArray();
		boolean isOK = true;
		for ( int i = 0; i < n; i++ ) for ( int j = 0; j < m; j++ ) {
			if ( i+1 < n && flag[i][j] == flag[i+1][j] ) {
				isOK = false;
				break;
			}
			if ( j+1 < m && flag[i][j] != flag[i][j+1] ) {
				isOK = false;
				break;
			}
		}
		if ( isOK ) ps.println( "YES" );
		else ps.println( "NO" );
	}
}
