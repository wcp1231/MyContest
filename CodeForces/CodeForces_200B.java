class CodeForces_200B {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		double juice = 0;
		for ( int i = 0; i < n; i++ ) {
			int per = sc.nextInt();
			juice += (per / 100.0);
		}
		ps.printf( "%.8f\n", juice / n * 100 );
	}
}
