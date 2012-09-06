/*
  如果输入的最后只有多个回车没有别的字符的话，
  用 hasNext 会返回 false ，而这题应该用 hashNextLine
 */
class CodeForces_5A {
	Scanner sc;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		sc = new Scanner( System.in );
		ps = System.out;
		int maxL = 0;
		Queue<String> q = new LinkedList<String>();
		StringBuffer ans = new StringBuffer(), star = new StringBuffer();
		while ( sc.hasNextLine() ) {
			String temp = sc.nextLine();
			maxL = Math.max( maxL, temp.length() );
			q.add( temp );
		}
		int t = 0;
		for ( int i = 0; i < maxL+2; i++ ) star.append( "*" );
		ans.append( star+"\n" );
		while ( !q.isEmpty() ) {
			String temp = q.poll();
			int l = maxL - temp.length();
			int f = 0;
			if ( l % 2 == 1 ) {
				f = (l + t) / 2;
				t ^= 1;
			} else f = l / 2;
			ans.append( "*" );
			for ( int i = 0; i < f; i++ ) ans.append( " " );
			ans.append( temp );
			for ( int i = f; i < l; i++ ) ans.append( " " );
			ans.append( "*\n" );
		}
		ans.append( star+"\n" );
		ps.println( ans.toString() );
	}
}
