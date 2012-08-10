	public int match() {
		int ans = 0;
		for ( int i = 0; i < n; i++ ) {
			visted = new boolean[ n ];
			if ( dfs( i ) ) ans++;
		}
		return ans;
	}
	
	public boolean dfs(int n) {
		for (int i = 0; i < n; i++) {
			if ( map[ n ][ i ] && !visted[ i ]) {
				visted[ i ] = true;
				int temp = match[ i ];
				match[ i ] = n;
				if ( temp == 0 || dfs( temp ) ) return true;
				match[ i ] = temp;
			}
		}
		return false;
	}
