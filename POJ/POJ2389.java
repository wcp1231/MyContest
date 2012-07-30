class POJ2389 {
	Scanner sc;
	PrintStream ps;
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		String num = sc.next();
		BigInteger a = new BigInteger( num );
		num = sc.next();
		BigInteger b = new BigInteger( num );
		ps.println( a.multiply( b ) );
	}
}
