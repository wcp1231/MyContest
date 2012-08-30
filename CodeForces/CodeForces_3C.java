/*
  WA了四次，考虑太不周全了
  第一次：没考虑一开始就不合法，而我是先判断胜负
  第二次：没考虑同时赢
  第三次：没考虑后手赢之后先手又下了
  第四次：没考虑斜线赢的
  没静下心来考虑
 */
class CodeForces_3C {
	InputReader ir;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		char[][] map = new char[ 3 ][ 3 ];
		map[ 0 ] = ir.next().toCharArray();
		map[ 1 ] = ir.next().toCharArray();
		map[ 2 ] = ir.next().toCharArray();
		int f = 0, s = 0;
		for ( int i = 0; i < 3; i++ ) {
			for ( int j = 0; j < 3; j++ ) {
				if ( map[ i ][ j ] == 'X' ) f++;
				else if ( map[ i ][ j ] == '0' ) s++;
			}
		}
		if ( f < s || f-s > 1 ) {
			ps.println("illegal");
			return ;
		}
		boolean fw = false, sw = false;
		for ( int i = 0; i < 3; i++ ) {
			if ( map[ i ][ 0 ] == map[ i ][ 1 ] && map[ i ][ 1 ] == map[ i ][ 2 ] ) {
				if ( map[ i ][ 0 ] == 'X' ) fw = true;
				else if ( map[ i ][ 0 ] == '0' ) sw = true;
			}
			if ( map[ 0 ][ i ] == map[ 1 ][ i ] && map[ 1 ][ i ] == map[ 2 ][ i ] ) {
				if ( map[ 0 ][ i ] == 'X' ) fw = true;
				else if ( map[ 0 ][ i ] == '0' ) sw = true;
			}
		}
		if ( map[ 0 ][ 0 ] == map[ 1 ][ 1 ] && map[ 1 ][ 1 ] == map[ 2 ][ 2 ] ) {
			if ( map[ 1 ][ 1 ] == 'X' ) fw = true;
			else if ( map[ 1 ][ 1 ] == '0' ) sw = true;
		} else if ( map[ 0 ][ 2 ] == map[ 1 ][ 1 ] && map[ 1 ][ 1 ] == map[ 2 ][ 0 ] ) {
			if ( map[ 1 ][ 1 ] == 'X' ) fw = true;
			else if ( map[ 1 ][ 1 ] == '0' ) sw = true;
		}
		if (( fw && sw) || (sw && f > s) || (fw && f == s)) ps.println("illegal");
		else if ( fw ) ps.println("the first player won");
		else if ( sw ) ps.println("the second player won");
		else if ( f == 5 ) ps.println("draw");
		else if ( f > s ) ps.println("second");
		else ps.println("first");
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
