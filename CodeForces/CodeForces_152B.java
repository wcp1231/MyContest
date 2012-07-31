class CodeForces_152B {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		int m = sc.nextInt();
		int cx = sc.nextInt();
		int cy = sc.nextInt();
		int k = sc.nextInt();
		long ans = 0;
		for ( int i = 0; i < k; i++ ) {
			int dx = sc.nextInt();
			int dy = sc.nextInt();
			int d1 = dx > 0 ? (n - cx) / dx : ((dx < 0) ? (cx - 1) / (-dx) : 0);
			int d2 = dy > 0 ? (m - cy) / dy : ((dy < 0) ? ( cy - 1) / (-dy) : 0);
			int d = 0;
			if ( dx == 0 && dy != 0 ) d = d2;
			else if ( dx != 0 && dy == 0 ) d = d1;
			else d = Math.min( d1, d2 );
			ans += d;
			cx += d * dx;
			cy += d * dy;
		}
		ps.println( ans );
	}
}
