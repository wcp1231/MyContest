class CodeForces_131A {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		String inp = ir.next();
		if ( inp.matches( "[a-z][A-Z]*" ) ) {
			inp = inp.substring( 0, 1 ).toUpperCase() + inp.substring( 1 ).toLowerCase();
		} else if ( inp.matches( "[A-Z]+" ) ) {
			inp = inp.toLowerCase();
		}
		ps.println( inp );
	}
}
