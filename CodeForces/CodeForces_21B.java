/*
  各种细节啊，WA 了N多次。。。最后用了计算几何的模板又WA了几次才过。。。
  关键是要理清逻辑啊，我这里写的还是太乱了。。。
 */
class CodeForces_21B {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int[] a = new int[2], b = new int[2], c = new int[2];
		a[ 0 ] = ir.nextInt();
		b[ 0 ] = ir.nextInt();
		c[ 0 ] = ir.nextInt();
		a[ 1 ] = ir.nextInt();
		b[ 1 ] = ir.nextInt();
		c[ 1 ] = ir.nextInt();
		double x1 = 0, x2 = 0, x3 = 0, x4 = 0, y1 = 0, y2 = 0, y3 = 0, y4 = 0;
		int cnt1 = 0, cnt2 = 0;
		if ( b[0] != 0 ) for ( int i = -100; i < 101; i++ ) {
			if ( cnt1 < 2 ) {
				x1 = x2;
				x2 = i;
				y1 = y2;
				y2 = (-(a[ 0 ] * i * 1.0 + c[ 0 ])) / b[ 0 ];
				cnt1++;
			}
			if ( cnt1 > 2 ) break;
		} else if ( a[0] != 0 ) for ( int i = -100; i < 101; i++ ) {
			if ( cnt1 < 2 ) {
				x1 = x2;
				x2 = (-(b[ 0 ] * i * 1.0 + c[ 0 ])) / a[ 0 ];;
				y1 = y2;
				y2 = i;
				cnt1++;
			}
			if ( cnt1 > 2 ) break;
		}
		if ( b[1] != 0 ) for ( int i = -100; i < 101; i++ ) {
			if ( cnt2 < 2 ) {
				x3 = x4;
				x4 = i;
				y3 = y4;
				y4 = (-(a[ 1 ] * i * 1.0 + c[ 1 ])) / b[ 1 ];
				cnt2++;
			}
			if ( cnt2 > 2 ) break;
		} else if ( a[1] != 0 ) for ( int i = -100; i < 101; i++ ) {
			if ( cnt2 < 2 ) {
				x3 = x4;
				x4 = (-(b[ 1 ] * i * 1.0 + c[ 1 ])) / a[ 1 ];
				y3 = y4;
				y4 = i;
				cnt2++;
			}
			if ( cnt2 > 2 ) break;
		}
		int t1 = 1, t2 = 1;
		if ( (a[0] == 0 && b[0] == 0 && c[0] == 0) ) t1 = -1;
		else if ( (a[0] == 0 && b[0] == 0 && c[0] != 0) ) t1 = 0;
		if ( (a[1] == 0 && b[1] == 0 && c[1] == 0) ) t2 = -1;
		else if ( (a[1] == 0 && b[1] == 0 && c[1] != 0) ) t2 = 0;
		if ( (t1 == -1 && t2 != 0) || (t2 == -1 && t1 != 0) ) ps.println( "-1" );
		else if ( t1 == 0 || t2 == 0 ) ps.println( "0" );
		else if ( parallel( x1, y1, x2, y2, x3, y3, x4, y4 ) ) {
			if ( dot_online_in( x3, y3, x1, y1, x2, y2 ) ) ps.println( "-1" );
			else ps.println( "0" );
		} else ps.println( "1" );
	}
	public double xmult( double x1, double y1, double x2, double y2, double x3, double y3 ) {
		return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
	}
	public boolean dot_online_in( double x, double y, double x1, double y1, double x2, double y2 ) {
		double eps = 1e-6;
		return zero( xmult(x,y,x1,y1,x2,y2) ) && (x1 - x) * (x2 - x) < eps && (y1 - y) * (y2 - y) < eps;
	}
	public boolean parallel( double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4 ) {
		return zero( (x1 - x2) * (y3 - y4) - (x3 - x4) * (y1 - y2) );
	}
	public boolean zero( double x ) {
		return (((x) > 0 ? (x) : (-x)) < 1e-6);
	}
}
