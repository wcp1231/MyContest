class CodeForces_203C {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n = sc.nextInt();
		int d = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int count = 0;
		Client clients;
		PriorityQueue< Client > q = new PriorityQueue< Client >();
		for ( int i = 0; i < n; i++ ) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int sum = a * x + b * y;
			if ( sum <= d ) {
				clients = new Client( x, y, sum, i+1 );
				q.add( clients );
				d -= sum;
				count ++;
			} else if ( !q.isEmpty() && sum < q.peek().sum ) {
				clients = q.poll();
				d += clients.sum;
				clients = new Client( x, y, sum, i+1 );
				q.add( clients );
				d -= sum;
			}
		}
		ps.println( count );
		while ( !q.isEmpty() ) ps.print( q.poll().id + " " );
		ps.println();
	}
}
class Client implements Comparable< Client > {
	int a, b, sum, id;
	public Client( int a, int b, int sum, int id ) {
		this.a = a;
		this.b = b;
		this.sum = sum;
		this.id = id;
	}
	@Override
	public int compareTo( Client o ) {
		return o.sum - this.sum;
	}
}
