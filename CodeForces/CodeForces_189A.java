class CodeForces_189A {
	
	Scanner sc;
	PrintStream ps;
	
	public void doit() {
		sc = new Scanner( System.in );
		ps = System.out;
		int x = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int[] dp = new int[ x+1 ];
		Arrays.fill( dp, -1 );
		dp[ 0 ] = 0;
		for ( int i = 0; i+a <= x; i++ ) if ( dp[ i ] != -1) dp[ i+a ] = Math.max( dp[ i+a ], dp[ i ]+1 );
		for ( int i = 0; i+b <= x; i++ ) if ( dp[ i ] != -1) dp[ i+b ] = Math.max( dp[ i+b ], dp[ i ]+1 );
		for ( int i = 0; i+c <= x; i++ ) if ( dp[ i ] != -1) dp[ i+c ] = Math.max( dp[ i+c ], dp[ i ]+1 );
		ps.println( dp[ x ] );
	}
}
