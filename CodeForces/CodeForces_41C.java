/*
  WA 了一次，我以为 replace 和 replaceFirst 一样，想不到却是和 replaceAll 一样
 */
class CodeForces_41C {
	InputReader ir;
	Scanner sc;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		String inp = ir.next();
		int len = inp.length();
		String temp = inp.substring( 1, len-1 );
		String ans = inp.substring(0, 1) + temp.replaceFirst( "at", "@" ).replaceAll( "dot", "." ) + inp.substring( len-1 );
		ps.println( ans );
	}
}
