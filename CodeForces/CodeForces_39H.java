class CodeForces_39H {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n = ir.nextInt();
		for ( int i = 1; i < n; i++ ) {
			ps.print( i );
			for ( int j = 2; j < n; j++ ) {
				ps.printf( "%3d", Integer.parseInt( Integer.toString( i*j, n) ) );
			}
			ps.println();
		}
	}
}
