// 不能遇到相等的就break，因为次小的可能会提前出现两次
class CodeForces_205A {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		int res = Integer.MAX_VALUE, ans = -1, t = 0;
		for ( int i = 0; i < n; i++ ) {
			int temp = sc.nextInt();
			if ( temp < res ) {
				res = temp;
				ans = i + 1;
				t = 1;
			}
			else if ( temp == res ) {
				t ++;
			}
		}
		if ( t > 1 ) ps.println( "Still Rozdil" );
		else ps.println( ans );
	}
}
