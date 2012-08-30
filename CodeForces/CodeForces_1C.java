/*
  题目是给三个点，这三个点是某个正多边形的三个顶点，求该正多边形的面积。
  这三个点组成的三角形的外接圆是这个正多边形的外接圆。先求出外接圆的半径
  由正弦定理：a/sinA = b/sinB = c/sinC = 2R, 可得 sinA = a / 2R
  三角形面积公式： S = (bc*sinA)/2, 可得 S = abc / 4R
  因此 R = abc / 4S
  角 A = acos((b^2 + c^2 - a^2) / (2bc)), 角 B、C 同理
  之后求这个多边形是正几边形 N = PI / gcd(A, B, C) //这里不解啊
  最后该正N边形的面积 = N * 1/2 * R^2 * sin( (2*PI) / N )
  其中 1/2 * R^2 * sin( (2*PI) / N ) 是三角形面积公式
 */
class CodeForces_1C {
	InputReader ir;
	PrintStream ps;
	double a, b, c, s, n, r, A, B, C;
	double eps = 1e-4;
	double[] x, y;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		x = new double[ 3 ];
		y = new double[ 3 ];
		for ( int i = 0; i < 3; i++ ) {
			x[ i ] = ir.nextDouble();
			y[ i ] = ir.nextDouble();
		}
		a = dis( 0, 1 );
		b = dis( 1, 2 );
		c = dis( 2, 0 );
		double t = (a + b + c) / 2;
		s = Math.sqrt(t * (t - a) * (t - b) * (t - c));
		r = (a * b * c) / (4 * s);
		A = Math.acos( (b*b + c*c - a*a) / (2 * b * c) );
		B = Math.acos( (a*a + c*c - b*b) / (2 * a * c) );
		C = Math.acos( (a*a + b*b - c*c) / (2 * a * b) );
		n = Math.PI / gcd( A, B, C );
		double res = (n / 2) * r * r * Math.sin( 2 * Math.PI / n );
		ps.printf( "%.6f", res );
		ps.println();
	}
	public double gcd( double x, double y, double z ) {
		return gcd( gcd(x, y), z );
	}
	public double gcd( double x, double y ) {
		while ( Math.abs(x) > eps && Math.abs(y) > eps ) {
			if ( x > y ) x -= Math.floor( x / y ) * y;
			else y -= Math.floor( y / x ) * x;
		}
		double t = x + y;
		t = t;
		return x + y;
	}
	public double dis( int i, int j ) {
		return Math.sqrt( Math.pow( x[i]-x[j], 2) + Math.pow( y[i]-y[j], 2) );
	}
}

class InputReader {
	private BufferedReader reader;
	private StringTokenizer tokenizer;
	
	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}
	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}
	public int nextInt() {
		return Integer.parseInt(next());
	}
	public long nextLong() {
		return Long.parseLong( next() );
	}
	public double nextDouble() {
		return Double.parseDouble( next() );
	}
}
