class CodeForces_186A {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		String str1 = sc.next();
		String str2 = sc.next();
		if ( str1.length() != str2.length() ) ps.println( "NO" );
		else {
			int len = str1.length();
			int count = 0;
			boolean flag = false, f = false;;
			char ch1 = 0, ch2 = 0;
			for ( int i = 0; i < len; i++ ) {
				if ( !flag && (str1.charAt( i ) != str2.charAt( i )) ) {
					ch1 = str1.charAt( i );
					ch2 = str2.charAt( i );
					flag = true;
					count++;
				} else if ( flag && (str1.charAt( i ) != str2.charAt( i )) ) {
					if ( ch1 == str2.charAt( i ) && ch2 == str1.charAt( i ) ) {
						f = true;
						flag = false;
					}
					count++;
				}
			}
			if ( !f || count > 2) ps.println( "NO" );
			else ps.println( "YES" );
		}
	}
}
