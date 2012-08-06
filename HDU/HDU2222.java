// AC自动机 非数组版
class HDU2222 {
	Scanner sc;
	PrintStream ps;
	AC_Automation ac;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int n, T = sc.nextInt();
		for ( int t = 0;t < T; t++ ) {
			n = sc.nextInt();
			ac = new AC_Automation();
			for ( int i = 0; i < n; i++ ) {
				String str = sc.next();
				ac.insertWord( str );
			}
			ac.build();
			String text = sc.next();
			ps.println( ac.query( text ) );
		}
	}
	
}
class AC_Automation {
	Node root;
	public AC_Automation() {
		root = new Node();
	}
	public int query( String text ) {
		int res = 0, idx = 0, len = text.length();
		Node p = root, temp;
		while ( idx < len ) {
			int nextChar = text.charAt( idx ) - 'a';
			while ( p.next[ nextChar ] == null && p != root ) p = p.fail;
			p = p.next[ nextChar ];
			p = ( p == null ) ? root : p;
			temp = p;
			while ( temp != root && temp.count != -1) {
				res += temp.count;
				temp.count = -1;
				temp = temp.fail;
			}
			idx++;
		}
		return res;
	}
	public void insertWord( String word ) {
		int idx = 0, len = word.length();
		Node p = root;
		while ( idx < len ) {
			int nextChar = word.charAt( idx ) - 'a';
			if ( p.next[ nextChar ] == null ) {
				p.next[ nextChar ] = new Node();
			}
			p = p.next[ nextChar ];
			idx++;
		}
		p.count++;
	}
	public void build() {
		Queue< Node > q = new LinkedList< Node >();
		q.add( root );
		Node p, temp;
		while ( !q.isEmpty() ) {
			temp = q.poll();
			p = null;
			for ( int i = 0; i < 26; i++ ) {
				if ( temp.next[ i ] != null ) {
					if ( temp == root ) temp.next[ i ].fail = root;
					else {
						p = temp.fail;
						while ( p != null ) {
							if ( p.next[ i ] != null ) {
								temp.next[ i ].fail = p.next[ i ];
								break;
							}
							p = p.fail;
						}
						if ( p == null ) temp.next[ i ].fail = root;
					}
					q.add( temp.next[ i ] );
				}
			}
		}
	}
}
class Node {
	Node fail;
	Node[] next;
	int count;
	public Node() {
		fail = null;
		next = new Node[ 26 ];
		count = 0;
	}
}
