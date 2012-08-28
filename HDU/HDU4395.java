/*
  虽说是简单DP，但是处理起来确实好麻烦啊。
  首先是把 double 转换成 int ，还要考虑负数
  所以就是 (double + 1) * 10000
  标程里说 double 是正数就 +EPS 否者就 -EPS
  因为会有这样的数据：
  1.9950 3
  2.0000
  0.0002
  -0.0520
  所以我将数据按正负分两个数组存，然后一正一负的处理。
  标程似乎是 DP+贪心，下次看看标程。。。
 */
class HDU4395 {
	PrintStream ps;
	int n;
	int[] dp;
	double[] mp, mn;
	public void doit() throws FileNotFoundException {
		//ir = new InputReader( System.in );
		Scan sn = new Scan( System.in );
		ps = System.out;
		int tarInt = 0, pp, pn;
		double target = 0, EPS = 1E-6;
		int T = sn.nextInt();
		dp = new int[ 35000 ];
		mp = new double[ 205 ];
		mn = new double[ 205 ];
		for ( int tt = 1; tt <= T; tt++ ) {
			target = sn.nextDouble();
			n = sn.nextInt();
			tarInt = d2i( target );
			pp = pn = 0;
			for ( int i = 0; i < n; i++ ) {
				double temp = sn.nextDouble();
				if ( temp > 0 ) mp[ pp++ ] = temp;
				else mn[ pn++ ] = temp;
			}
			Arrays.fill( dp, -1 );
			Arrays.sort( mp, 0, pp );
			Arrays.sort( mn, 0, pn );
			dp[ 10000 ] = 0;
			while ( pp > 0 || pn > 0 ) {
				if ( pp > 0 ) {
					double t = mp[--pp];
					for ( int j = 34999; j >= 0; j-- ) if (dp[j] != -1) {
						double temp = i2d(j) + t;
						if ( temp+1 >= EPS && temp-2 < EPS ) {
							dp[ d2i(temp) ] = dp[ j ] + 1;
						}
					}
				}
				if ( pn > 0 ) {
					double t = mn[--pn];
					for ( int j = 0; j < 35000; j++ ) if (dp[j] != -1) {
						double temp = i2d( j ) + t;
						if ( temp+1 >= EPS && temp-2 < EPS ) {
							dp[ d2i(temp) ] = dp[ j ] + 1;
						}
					}
				}
			}
			int ans = Integer.MAX_VALUE;
			for ( int i = 0; i < 30000; i++ ) if ( dp[i] != -1 ) {
				int a = Math.abs( ans - tarInt );
				int b = Math.abs( i - tarInt );
				if ( b < a ) ans = i;
			}
			ps.printf( "%.4f", i2d(ans) );
			ps.println();
		}
	}
	public int d2i( double num ) {
		return (int) ((num + 1.000005) * 10000);
	}
	public double i2d( int num ) {
		return ((double) num) / 10000.0 - 1 + 1E-9;
	}
}
class Scan {
    InputStream inp;
    
    public Scan(InputStream in) {
        inp = in;
    }
    public int nextInt() {
        int res = 0;
        int t;
        try {
            t = inp.read();
            if (t == -1) return -1;
            while (t != -1 && (t > '9' || t < '0')) t = inp.read();
            while (t != -1 && t >= '0' && t <= '9') {
                res = res * 10 + (t - '0');
                t = inp.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    public double nextDouble() {
    	double res = 0, Dec = 0.1;
    	boolean isN = false, isD = false;
    	int t;
    	try {
			t = inp.read();
			if ( t == -1 ) return -1;
			while ( t != '-' && t != '.' && (t < '0' || t > '9') ) t = inp.read();
			if ( t == '-' ) {
				isN = true;
				res = 0;
			} else if ( t == '.' ) {
				isD = true;
				res = 0;
			} else res = (double) t - '0';
			if ( !isD ) {
				t = inp.read();
				while ( t >= '0' && t <= '9' ) {
					res = res * 10 + (t - '0');
					t = inp.read();
				}
			}
			if ( t != '.' ) {
				if ( isN ) res = -res;
				return res;
			} else {
				t = inp.read();
				while ( t >= '0' && t <= '9' ) {
					res += Dec * (t - '0');
					Dec *= 0.1;
					t= inp.read();
				}
			}
			if ( isN ) res = -res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return res;
    }
}
