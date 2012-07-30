class POJ1258 {
	
	Scanner sc;
	PrintStream ps;
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		MST_Prime mst;
		while ( sc.hasNext() ) {
			int n = sc.nextInt();
			mst = new MST_Prime( n, n*n );
			for ( int i = 1; i <= n; i++ ) {
				for ( int j = 1; j <= n; j++ ) {
					int c = sc.nextInt();
					mst.addEdge( i, j, c );
				}
			}
			ps.println( mst.prime() );
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
