/*
  RE 了一次又 TLE 了一次。。。
  RE 是忘了考虑数组越界了。。。
 */
class CodeForces_16B {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n, m;
		n = ir.nextInt();
		m = ir.nextInt();
		Container[] con = new Container[ m ];
		for ( int i = 0; i < m; i++ ) {
			int a = ir.nextInt();
			int b = ir.nextInt();
			con[ i ] = new Container( a, b );
		}
		Arrays.sort( con );
		int ans = 0, p = 0;
		while ( p < con.length && n > 0 ) {
			if ( n > 0 && con[p].a <= n ) {
				n -= con[ p ].a;
				ans += con[ p ].a * con[ p ].b;
			} else if ( n > 0 && con[p].a > n ) {
				ans += n * con[ p ].b;
				n = 0;
			}
			p++;
		}
		ps.println( ans );
	}
}
class Container implements Comparable<Container> {
	int a, b;
	public Container( int a, int b ) {
		this.a = a;
		this.b = b;
	}
	@Override
	public int compareTo(Container o) {
		return o.b - b;
	}
}
