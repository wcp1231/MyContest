class CodeForces_5A {
	Scanner sc;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		sc = new Scanner( System.in );
		ps = System.out;
		int num = 0, ans = 0;
		while ( sc.hasNext() ) {
			String cmd = sc.nextLine();
			if ( cmd.startsWith("+") ) {
				num++;
			} else if ( cmd.startsWith("-") ) {
				num--;
			} else {
				ans += num * cmd.substring( cmd.indexOf(':')+1 ).length();
			}
		}
		ps.println( ans );
	}
}
