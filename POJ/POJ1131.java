class POJ1131 {
	Scanner sc;
	PrintStream ps;
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		while ( sc.hasNext() ) {
			String num = sc.next();
			ps.print(num + " [8] = ");
			BigDecimal base8 = new BigDecimal( 8 );
			BigDecimal base = new BigDecimal( 8 );
			BigDecimal ans = new BigDecimal( 0 );
			int len = num.length();
			for ( int i = 2; i < len; i++ ) {
				ans = ans.add( new BigDecimal( num.charAt(i)-'0' ).divide(base) );
				base = base.multiply( base8 );
			}
			ps.println( ans + " [10]" );
		}
	}
}
