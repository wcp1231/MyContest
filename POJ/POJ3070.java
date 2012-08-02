class POJ3070 {
	Scanner sc;
	PrintStream ps;
	public void doit() {
		sc = new Scanner(System.in);
		int n = sc.nextInt();
		while ( n != -1 ) {
			if ( n != 0 ) {
				Matrix ans = new Matrix();
				ans.pow( n );
				ps.println( ans.num[ 1 ][ 1 ] );
			} else
				ps.println( "0" );
			n = sc.nextInt();
		}
	}
}

class Matrix {
	int[][] num = new int[ 2 ][ 2 ];
	public Matrix() {
		num[ 0 ][ 0 ] = num[ 0 ][ 1 ] = num[ 1 ][ 0 ] = 1;
		num[ 1 ][ 1 ] = 0;
	}
	public Matrix(int a11, int a12, int a21, int a22) {
		num[ 0 ][ 0 ] = a11;
		num[ 0 ][ 1 ] = a12;
		num[ 1 ][ 0 ] = a21;
		num[ 1 ][ 1 ] = a22;
	}
	public void mul(Matrix o) {
		int a11 = (num[ 0 ][ 0 ] * o.num[ 0 ][ 0 ] + num[ 0 ][ 1 ] * o.num[ 1 ][ 0 ]) % 10000;
		int a12 = (num[ 0 ][ 0 ] * o.num[ 0 ][ 1 ] + num[ 0 ][ 1 ] * o.num[ 1 ][ 1 ]) % 10000;
		int a21 = (num[ 1 ][ 0 ] * o.num[ 0 ][ 0 ] + num[ 1 ][ 1 ] * o.num[ 1 ][ 0 ]) % 10000;
		int a22 = (num[ 1 ][ 0 ] * o.num[ 0 ][ 1 ] + num[ 1 ][ 1 ] * o.num[ 1 ][ 1 ]) % 10000;
		num[ 0 ][ 0 ] = a11;
		num[ 0 ][ 1 ] = a12;
		num[ 1 ][ 0 ] = a21;
		num[ 1 ][ 1 ] = a22;
	}
	public void pow( int n ) {
		Matrix temp = new Matrix();
		while ( n > 0 ) {
			if ( (n & 1) == 1 ) {
				this.mul( temp );
			}
			temp.mul( temp );
			n >>= 1;
		}
	}
}
