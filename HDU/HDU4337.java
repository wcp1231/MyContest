// 构造 哈密顿回路
class HDU4337 {
	Scanner sc;
	PrintStream ps;
	boolean[] used;
	boolean[][] map;
	int n, m, count, S, T;
	int[] ans;
	public void doit() {
		sc = new Scanner( System.in );
		
		ps = System.out;
		while ( sc.hasNext() ) {
			n = sc.nextInt();
			m = sc.nextInt();
			map = new boolean[ n+1 ][ n+1 ];
			for ( int i = 0; i < m; i++ ) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				map[ a ][ b ] = map[ b ][ a ] = true;
			}
			used = new boolean[ n+1 ];
			ans = new int[ n+1 ];
			count = 0;
			S = 1;
			used[ S ] = true;
			ans[ count++ ] = S;
			for ( T = 2; T <= n; T++ ) if ( map[ S ][ T ] ) break;
			used[ T ] = true;
			ans[ count++ ] = T;
			int p = 1;
			while ( count < n ) {
				boolean flag = true;
				while ( flag ) {
					flag = false;
					for ( int i = 1; i <= n; i++ ) if ( !used[ i ] && map[ T ][ i ] ) {
						ans[ count++ ] = i;
						used[ i ] = true;
						T = i;
						flag = true;
					}
				}
				if ( count < n ) {
					for ( int i = p; i < count-1; i++ ) if ( map[ ans[i] ][ T ] && map[ ans[i+1] ][ S ] ) {
						reverse( i+1, count-1 );
						T = ans[ count-1 ];
						p ++;
						if ( p >= count-1 ) p = 1;
						break;
					}
				}
				if ( !map[ S ][ T ] ) {
					for ( int i = p; i < count-1; i++ ) if ( map[ ans[i] ][ T ] && map[ ans[i+1] ][ S ] ) {
						reverse( i+1, count-1 );
						T = ans[ count-1 ];
						p ++;
						if ( p >= count-1 ) p = 1;
						break;
					}
				}
			}
			ps.print( ans[ 0 ] );
			for ( int i = 1; i < count; i++ ) ps.print( " " + ans[ i ] );
			ps.println();
		}
	}
	public void reverse( int s, int t ) {
		int a = s;
		int b = t;
		while ( a < b ) {
			int temp = ans[ a ];
			ans[ a ] = ans[ b ];
			ans[ b ] = temp;
			a ++;
			b --;
		}
	}
}
