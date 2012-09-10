/*
  一开始在 defragment 的时候没有更新 Block 里的信息，导致 Test8 RE
  然后修改的时候没彻底仔细的改，又 RE、WA 了几次。。。
 */
class CodeForces_20A {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n, m;
		n = ir.nextInt();
		m = ir.nextInt();
		Memory mem = new Memory( m );
		for ( int i = 0; i < n; i++ ) {
			String cmd = ir.next();
			int args;
			if ( cmd.equals("alloc") ) {
				args = ir.nextInt();
				int res = mem.alloc( args );
				if ( res != 0 ) ps.println( res );
				else ps.println( "NULL" );
			} else if ( cmd.equals("erase") ) {
				args = ir.nextInt();
				boolean res = mem.erase( args );
				if ( !res ) ps.println( "ILLEGAL_ERASE_ARGUMENT" );
			} else if ( cmd.equals("defragment") ) {
				mem.defragment();
			}
		}
	}
}
class Memory {
	int size, idx;
	int[] memory;
	ArrayList<Block> list;
	public Memory( int n ) {
		size = n;
		idx = 1;
		memory = new int[ n ];
		list = new ArrayList<Block>();
	}
	public int alloc( int n ) {
		for ( int i = 0; i < size; i++ ) if ( memory[i] == 0 ) {
			boolean flag = false;
			int j;
			for ( j = 0; j < n && j+i < size; j++ ) if ( memory[j+i] != 0 ) {
				flag = true;
				break;
			}
			if ( !flag && j == n ) {
				list.add( new Block(i, i+n, idx) );
				for ( j = 0; j < n; j++ ) memory[ i+j ] = idx;
				return idx++;
			}
		}
		return 0;
	}
	public boolean erase( int n ) {
		for ( Block p : list ) {
			if ( p.id == n ) {
				for ( int i = p.start; i < p.end; i++ ) memory[i] = 0;
				list.remove( p );
				return true;
			}
		}
		return false;
	}
	public void defragment() {
		int preID = -1, pre = 0;
		for ( int i = 0; i < size; i++ ) if ( memory[i] != 0 && memory[i] != preID ) {
			int temp = memory[ i ], j;
			Block p = null;
			for ( j = 0; j < list.size(); j++ ) if ( list.get(j).id == temp ) {
				p = list.get( j );
				break;
			}
			for ( j = p.start; j < p.end; j++ ) memory[ j ] = 0;
			temp = p.end - p.start;
			p.start = pre;
			p.end = p.start + temp;
			for ( j = p.start; j < p.end; j++ ) memory[ j ] = p.id;
			pre = p.end;
			preID = p.id;
		}
	}
}
class Block {
	int start, end, id;
	public Block( int s, int e, int id ) {
		start = s;
		end = e;
		this.id = id;
	}
}
