/*
  本来想用搜索的，后来发现不用这么麻烦，直接遍历，用 set 判断重复即可
 */
class CodeForces_6B {
	InputReader ir;
	Scanner sc;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n, m;
		char c;
		n = ir.nextInt();
		m = ir.nextInt();
		c = ir.next().charAt(0);
		char[][] map = new char[ n ][ m ];
		HashSet<Character> set = new HashSet<Character>();
		for ( int i = 0; i < n; i++ ) map[ i ] = ir.next().toCharArray();
		int ans = 0;
		for ( int i = 0; i < n; i++ ) for ( int j = 0; j < m; j++ ) if ( map[i][j] == c ) {
			if ( i > 0 && map[i-1][j] != '.' && map[i-1][j] != c ) {
				if ( !set.contains(map[i-1][j]) ) {
					ans++;
					set.add( map[i-1][j] );
				}
			}
			if ( j > 0 && map[i][j-1] != '.' && map[i][j-1] != c ) {
				if ( !set.contains(map[i][j-1]) ) {
					ans++;
					set.add( map[i][j-1] );
				}
			}
			if ( i < n-1 && map[i+1][j] != '.' && map[i+1][j] != c ) {
				if ( !set.contains(map[i+1][j]) ) {
					ans++;
					set.add( map[i+1][j] );
				}
			}
			if ( j < m-1 && map[i][j+1] != '.' && map[i][j+1] != c ) {
				if ( !set.contains(map[i][j+1]) ) {
					ans++;
					set.add( map[i][j+1] );
				}
			}
		}
		ps.println( ans );
	}
}
