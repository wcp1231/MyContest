class CodeForces_208B {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		String str = sc.next();
		str = str.replaceAll( "WUB", " " );
		str = str.replaceAll( "^[ ]+", "" );
		str = str.replaceAll( "[ ]+$", "" );
		ps.println( str );
	}
}
