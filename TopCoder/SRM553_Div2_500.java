/*
  做法大概是将-1 分别改成 0 1 2 然后比较结果。
  WA的原因是if ( ret != res && res <= w ) 这句少个等号，使得这样的数据WA了
  {-1} 1
 */
public class Suminator
{
	public int findMissing(int[] pro, int w)
	{
		int ans = Integer.MAX_VALUE, pos = 0;
		for ( int i = 0; i < pro.length; i++ ) if ( pro[i] == -1 ) pos = i;
		pro[pos] = 0;
		Long res = opt( pro );
		if ( res == w ) ans = 0;
		pro[pos] = 1;
		res = opt( pro );
		pro[pos] = 2;
		long ret = opt( pro );
		if ( ret != res && res <= w ) {
			ans = (int) Math.min( ans, w-res+1 );
		}
		if ( ans == Integer.MAX_VALUE ) ans = -1;
		return ans;
	}
	public long opt( int[] pro ) {
		long res = 0; 
		int p = 0;
		long[] sta = new long[60];
		for ( int i = 0; i < pro.length; i++ ) {
			if ( pro[i] == 0 ) {
				Long a = (long) 0;
				Long b = (long) 0;
				if ( p > 0 ) a = sta[--p];
				if ( p > 0 ) b = sta[--p];
				sta[p++] = a + b;
			} else {
				sta[p++] = pro[i];
			}
		}
		if ( p > 0 ) res = sta[--p];
		return res;
	}
}
