class CodeForces_192A {
	Scanner sc;
	PrintStream ps;
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int num = sc.nextInt();
		int n = (int) Math.sqrt( num );
		boolean flag = true;
		for ( int i = 1; i <= n; i++ ) {
			int x = num - (i * (i+1) / 2);
			if ( x == 0 ) continue;
			int m = (int) Math.sqrt( x*2 );
			int y = (i * (i+1)) / 2 + (m * (m+1)) / 2;
			if ( y == num ) {
				ps.println( "YES" );
				flag = false;
				break;
			}
		}
		if ( flag ) ps.println( "NO" );
	}
}
