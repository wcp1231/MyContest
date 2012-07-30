class CodeForces_194A {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		int k = sc.nextInt();
		int d = k - 2 * n;
		ps.println( n - d >= 0 ? n - d : 0 );
	}
}
