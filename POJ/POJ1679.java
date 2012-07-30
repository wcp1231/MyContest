class POJ1679 {
	
	Scanner sc;
	PrintStream ps;
	int[][] map;
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		MST_Prime mst;
		int T = sc.nextInt();
		for ( int t = 0; t < T; t++ ) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			map = new int[n+1][n+1];
			mst = new MST_Prime( n, m );
			for ( int i = 1; i <= n; i++ ) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				int w = sc.nextInt();
				map[u][v] = map[v][u] = w;
				mst.addEdge( u, v, w );
				mst.addEdge( v, u, w );
			}
			int ans = mst.prime();
			System.out.println( ans );
		}
	}
}
class MST_Prime {
	Edge[] edge;
	int[] head;
	int maxN, maxE, numE;
	public MST_Prime( int maxN, int maxE ) {
		this.maxN = maxN + 1;
		this.maxE = 2 * maxE  + 1;
		head = new int[this.maxN];
		edge = new Edge[this.maxE];
		numE = 1;
	}
	public void addEdge( int u, int v, int cost ) {
		edge[numE] = new Edge(v, cost, head[u]);
		head[u] = numE++;
	}
	public int prime() {
		int res = 0;
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		boolean[] used = new boolean[maxN];
		used[1] = true;
		int p = head[1], k = 1;
		while ( p != 0 ) {
			q.add( edge[p] );
			p = edge[p].next;
		}
		while ( k < maxN-1 ) {
			Edge e = q.poll();
			if ( !used[e.to] ) {
				used[e.to] = true;
				res += e.cost;
				k ++;
				p = head[e.to];
				while ( p != 0 ) {
					q.add( edge[p] );
					p = edge[p].next;
				}
			}
		}
		return res;
	}
}
class Edge implements Comparable<Edge> {
	int cost, to, next;
	public Edge( int to, int cost, int next ) {
		this.to = to;
		this.cost = cost;
		this.next = next;
	}
	@Override
	public int compareTo( Edge o ) {
		return cost - o.cost;
	}
}
