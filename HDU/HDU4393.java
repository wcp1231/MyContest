/*
  用100个堆，将人按第二速度存进堆里，然后直接计算就行。
  这题直接一个结果一个结果的输出需要 5S 会超时
  用 StringBuffer 将答案存了最后输出只用 2S
  以后都用 StringBuffer 了
 */
class HDU4393 {
	PrintStream ps;
	int n;
	public void doit() throws FileNotFoundException {
		Scan sn = new Scan( System.in );
		ps = System.out;
		ArrayList<PriorityQueue<People>> list = new ArrayList<PriorityQueue<People>>();
		for ( int i = 0; i < 101; i++ ) list.add( new PriorityQueue<People>() );
		int T = sn.nextInt();
		for ( int tt = 1; tt <= T; tt++ ) {
			n = sn.nextInt();
			for ( int i = 0; i < n; i++ ) {
				int a = sn.nextInt();
				int b = sn.nextInt();
				list.get( b ).add( new People( a, b, i+1 ) );
			}
			ps.println( "Case #" + tt + ":" );
			int idx = -1, max = 0, id = -1;
			StringBuffer ans = new StringBuffer();
			for ( int i = 0; i < n; i++ ) {
				People temp;
				max = 0;
				for ( int j = 1; j < 101; j++ ) {
					int t = 0;
					temp = list.get( j ).peek();
					if ( temp != null ) {
						t = temp.a + i * temp.b;
						if ( t > max ) {
							max = t;
							idx = j;
							id = temp.idx;
						} else if ( t == max && id > temp.idx ) {
							max = t;
							idx = j;
							id = temp.idx;
						}
					}
				}
				if ( i != 0 ) ans.append( " " );
				temp = list.get( idx ).poll();
				ans.append( temp.idx );
			}
			ps.println( ans.toString() );
		}
	}
}
class People implements Comparable<People> {
	int a, b, idx;
	public People( int a, int b, int idx ) {
		this.a = a;
		this.b = b;
		this.idx = idx;
	}
	@Override
	public int compareTo(People o) {
		if ( o.a == a ) return idx - o.idx;
		return o.a - a;
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
}
