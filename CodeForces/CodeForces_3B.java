/*
  这道题 WA 了好多次，各种细节没考虑好。
  最严重的是思路错了，本来想这么贪心的：
  因为船的容量只有 1 和 2 两种，所以就想两个容量 1 的船和一个容量为 2 的船比较
  然后如果两个容量 1 的船比容量 2 的船更优，则结果加上两个容量为 1 的，这里 WA 了
  必须每次都只加一个容量为 1 的才行，而不是加两个容量为 1 的。
 */
class CodeForces_3B {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n = ir.nextInt(), v = ir.nextInt(), op = 0, tp = 0;
		Boat[] one = new Boat[ n ], two = new Boat[ n ];
		for ( int i = 0; i < n; i++ ) {
			int t = ir.nextInt();
			int c = ir.nextInt();
			if ( t == 1 ) one[ op++ ] = new Boat( c, i+1 );
			else if ( t == 2 ) two[ tp++ ] = new Boat( c, i+1 );
		}
		Arrays.sort( one, 0, op );
		Arrays.sort( two, 0, tp );
		int res = 0;
		StringBuffer ans = new StringBuffer();
		while ( v > 0 ) {
			Boat t = new Boat( 0, 0 ), o1 = new Boat( 0, 0 ), o2 = new Boat( 0, 0 );
			if ( tp > 0 ) t = two[ tp-1 ]; // 这里不能直接写 --tp
			if ( op > 0 ) o1 = one[ op-1 ]; // 因为这样的循环每次只会选一组
			if ( op > 1 ) o2 = one[ op-2 ]; // 但是用 --tp 会错过之前没选的
			if ( t.c > o1.c+o2.c && v >= 2 ) {
				v -= 2;
				res += t.c;
				ans.append( " " + t.id );
				tp--;
			} else {
				if ( o1.id != 0 ) {
					v--;
					res += o1.c;
					ans.append( " " + o1.id );
					op--;
				}
			} // 这里只用更新 o1 即可
			if ( op == 0 && (tp == 0 || v == 1) ) break;
		}
		ps.println( res );
		if ( res > 0 ) ps.println( ans.toString().substring(1) );
	}
}
class Boat implements Comparable<Boat> {
	int c, id;
	public Boat( int c, int id ) {
		this.c = c;
		this.id = id;
	}
	@Override
	public int compareTo(Boat o) {
		return c - o.c;
	}
	
}
class InputReader {
	private BufferedReader reader;
	private StringTokenizer tokenizer;
	
	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}
	public boolean hasNext() {
		if (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (Exception e) {
				return false;
			}
		}
		return tokenizer.hasMoreTokens();
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
