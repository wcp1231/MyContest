/*
  不知道说啥。。。BFS + 二维的判重 过的。。。
  不知道能不能一维判重。。。
 */
public class XorTravelingSalesman
{
	public int maxProfit(int[] cv, String[] roads)
	{
		int ans = 0;
		int len = cv.length;
		int[] dp = new int[ 1024 ];
		int[][] visted = new int[ 1024 ][ len ];
		Queue<City> q = new LinkedList<City>();
		dp[ cv[0] ] = 1;
		City t = new City(0, cv[0]);
		q.add( t );
		while ( !q.isEmpty() ) {
			t = q.poll();
			for ( int i = 0; i < len; i++ ) if ( roads[t.cur].charAt(i) == 'Y' ) {
				int temp = t.val ^ cv[ i ];
				City nxt = new City( i, temp );
				if ( visted[temp][i] == 0 ) {
					dp[ temp ] = 1;
					visted[temp][i] = 1;
					q.add( nxt );
				}
			}
		}
		for ( int i = 1023; i >= 0; i-- ) 
			if ( dp[i] > 0 ) {
				ans = i;
				break;
			}
		return ans;
	}
	
	
}
class City {
	int cur, val;
	int[] visted;
	public City( int c, int v ) {
		cur = c;
		val = v;
	}
}
