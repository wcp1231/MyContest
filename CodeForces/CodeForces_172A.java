class CodeForces_172A {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		int len = Integer.MAX_VALUE;
		String[] str = new String[ n ];
		for ( int i = 0; i < n; i++ ) {
			str[ i ] = sc.next();
			len = Math.max( str.length, len );
		}
		boolean flag = false;
		int ans = 0;
		for ( int i = 0; i < len; i++ ) {
			for ( int j = 1; j < n; j++ ) if ( str[ j-1 ].charAt( i ) != str[ j ].charAt( i ) ) {
				flag = true;
				ans = i;
			}
			if ( flag ) break;
		}
		ps.println( ans );
	}
}
