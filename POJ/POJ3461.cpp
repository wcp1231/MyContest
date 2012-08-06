#include <cstdio>
#include <iostream>
#include <cstring>

using namespace std;

int next[10005], ans;
char t[10005], s[1000005];

void getNext()
{
  int lent = strlen( t );
  next[ 0 ] = -1;
  int i = 0, j = -1;
  while ( i < lent )
  {
    if ( j < 0 || t[ i ] == t[ j ] )
    {
      ++i, ++j;
      if ( t[ i ] != t[ j ] ) next[ i ] = j;
      else next[ i ] = next[ j ];
    }
    else j = next[ j ];
  }
}
int kmp()
{
  int lens = strlen( s );
  int lent = strlen( t );
  // getNext( t ) || calnext( t );
  int i = 0, j = 0;
  while ( i < lens && j < lent)
  {
    if ( j < 0 || s[ i ] == t[ j ] )
    {
      ++i, ++j;
    }
    else j = next[ j ];
    if ( j == lent )
    {
      j = next[ j ];
      ans++;
      //return i - j;
    }
  }
  return -1;
}
int main()
{
  int T;
  scanf("%d", &T);
  while( T-- )
  {
    scanf("%s", t);
    scanf("%s", s);
    ans = 0;
    getNext();
    kmp();
    printf("%d\n", ans);
  }
  return 0;
}

