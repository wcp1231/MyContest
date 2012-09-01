/*
  这题的做法是贪心，每次遇到一个 ? 就尝试将这个问号设为右边括号
  当 blance 小于 0，即当先点的左边左括号数量小于有括号时
  将 之前的 被改成右边括号的 左右花费差最大的 问号修改为左括号
  这题 WA 了两次，一次是因为最后的结果超过了 int
  另一次是类似 (?( 这样的数据，我遍历完之后没考虑 blance 是否为零
 */
class CodeForces_3D {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		PriorityQueue<Change> q = new PriorityQueue<Change>();
		char[] str = ir.next().toCharArray();
		int blance = 0;
		long ans = 0;
		for ( int i = 0; i < str.length; i++ ) {
			if ( str[i] == '?' ) {
				int l = ir.nextInt();
				int r = ir.nextInt();
				blance--;
				str[ i ] = ')';
				ans += r;
				q.add( new Change(l-r, i) );
			} else if ( str[i] == '(' ) blance++;
			else if ( str[i] == ')' ) blance--;
			if ( blance < 0 ) {
				if ( q.isEmpty() ) {
					ps.println("-1");
					return ;
				}
				Change temp = q.poll();
				blance += 2;
				str[ temp.pos ] = '(';
				ans += temp.cost;
			}
		}
		if ( blance == 0 ) {
			ps.println( ans );
			ps.println( String.valueOf(str) );
		} else {
			ps.println("-1");
		}
	}
}
class Change implements Comparable<Change> {
	int cost, pos;
	public Change( int c, int p ) {
		cost = c;
		pos = p;
	}
	@Override
	public int compareTo(Change o) {
		return cost - o.cost;
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
