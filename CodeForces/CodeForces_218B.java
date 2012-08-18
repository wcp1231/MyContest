class CodeForces_218B {
	InputReader ir;
	PrintStream ps;
	int n, m;
	int[] plant;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		PriorityQueue<Integer> q2 = new PriorityQueue<Integer>( 1, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		n = ir.nextInt();
		m = ir.nextInt();
		plant = new int[ m ];
		for ( int i = 0; i < m; i++ ) {
			plant[i] = ir.nextInt();
			q.add( plant[i] );
			q2.add( plant[i] );
		}
		int maxAns = 0, minAns = 0, peo = n;
		while ( peo > 0 && !q2.isEmpty() ) {
			int temp = q2.poll();
			maxAns += temp;
			temp--;
			peo--;
			if ( temp > 0 ) q2.add( temp );
		}
		peo = n;
		while ( peo > 0 && !q.isEmpty() ) {
			int temp = q.poll();
			minAns += temp;
			temp--;
			peo--;
			if ( temp > 0 ) q.add( temp );
		}
		ps.println(maxAns + " " + minAns);
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
