/*
  比赛的时候没时间想。这道题要想的角度对了并且思路要清晰才行，题意简单坑多。。
 */
class LeftRightDigitsGame
{
	public String minNumber(String digits)
	{
		String ans = "";
		char[] inp = digits.toCharArray();
		int p = -1, min = '9';
		for ( int i = 0; i < inp.length; i++ ) if ( inp[i] != '0' && inp[i] <= min ) {
			min = inp[ i ];
			p = i;
		}
		ans = "" + inp[ 0 ];
		for ( int i = 1; i < inp.length; i++ ) {
			if ( p == i ) ans = inp[i] + ans;
			else if ( p < i ) ans += inp[i];
			else if ( ans.charAt(0) >= inp[i] ) ans = inp[i] + ans;
			else ans += inp[i];
		}
		return ans;
	}
}
