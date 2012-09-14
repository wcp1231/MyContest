/*
  暴力过的。。。
 */
public class ChocolateBar
{
	public int maxLength(String letters)
	{
		int ans = 0;
		int[] ch;
		int len = letters.length();
		char[] inp = letters.toCharArray();
		boolean flag;
		for ( int l = 0; l < len; l++ ) {
			for ( int i = 0; i < len; i++ ) {
				int temp = 0;
				ch = new int[ 26 ];
				flag = true;
				for ( int j = i; j+l < len; j++ ) {
					ch[ inp[j]-'a' ]++;
					if ( ch[ inp[j]-'a' ] > 1 ) {
						flag = false;
						break;
					}
					temp++;
				}
				if ( flag ) {
					ans = Math.max( ans, temp );
				}
			}
		}
		return ans;
	}
}
