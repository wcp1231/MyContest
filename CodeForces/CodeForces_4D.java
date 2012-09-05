/*
  这道题 WA 了四次啊。。
  第一次：向前遍历的时候没判断之前的信封能否放的下卡片，导致多计算了之前原本不合法
  第二次：答案为 0 的时候，不用输出序列。。
  第三次：我因为用的 StringBuffer 存答案，然后再反转输出
          所以本应该输出 13 的反而输出了 31
  第四次：我错误的使用 将 int 反转 的思路，导致 30 会反转成 3
          应该将数字看成字符串
 */
class CodeForces_4D {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n = ir.nextInt();
		int w = ir.nextInt();
		int h = ir.nextInt();
		Envelope[] env = new Envelope[ n ];
		for ( int i = 0; i < n; i++ ) {
			int ww = ir.nextInt();
			int hh = ir.nextInt();
			env[ i ] = new Envelope( ww, hh, i+1 );
		}
		Arrays.sort( env );
		int[] dp = new int[ n ], pre = new int[ n+1 ];
		Arrays.fill( pre, -1 );
		for ( int i = 0; i < n; i++ ) {
			if ( dp[i] == 0 ) {
				if ( w < env[i].w && h < env[i].h ) {
					dp[i] = 1;
				}
				for ( int j = i-1; j >= 0; j-- ) {
					if ( env[j].w < env[i].w && env[j].h < env[i].h && dp[j] != 0 && dp[i] < dp[j]+1) {
						dp[ i ] = dp[ j ] + 1;
						pre[ i ] = j;
					}
				}
			}
		}
		int ans = 0, idx = 0;
		for ( int i = 0; i < n; i++ ) {
			if ( dp[i] > ans ) {
				ans = dp[ i ];
				idx = i;
			}
		}
		ps.println( ans );
		if ( ans > 0 ) {
			StringBuffer res = new StringBuffer();
			while ( idx != -1 ) {
				res.append( reverseInt(env[idx].id) + " " );
				idx = pre[ idx ];
			}
			ps.println( res.reverse().toString().substring(1) );
		}
	}
	public String reverseInt( int n ) {
		StringBuffer res = new StringBuffer();
		res.append( n );
		return res.reverse().toString();
	}
}
class Envelope implements Comparable<Envelope> {
	int w, h, id;
	public Envelope( int w, int h, int id ) {
		this.w = w;
		this.h = h;
		this.id = id;
	}
	public int compareTo(Envelope o) {
		if ( w == o.w ) return h - o.h;
		return w - o.w;
	}
}
