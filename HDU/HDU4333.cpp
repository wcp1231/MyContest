#include <cstdio>
#include <iostream>
#include <cstring>

using namespace std;

int next[200005];
char str[200005];
int len;

void getNext()
{
  int l = 1, r = -1, i, j;
  for ( next[0] = 0, i = 1; str[i]; ++i )
  {
    if ( i+next[i-l]-1 < r ) next[i] = next[i-l];
    else 
    {
      for (j = (r-i+1 > 0 ? r-i+1 : 0); str[i+j] && str[i+j]==str[j];++j) ;
      next[i] = j;
      l = i;
      r = i+j-1;
    }
  }
  next[0] = i;
}

void calnext()
{
  int j = 0, i;
  int m, l;
  len = strlen( str );
  while (str[0+j] == str[1+j]) j = j+1;
  next[1] = j;
  int k = 1;
  for ( i = 2; i < len; i++ )
  {
    m = k + next[k] - 1;
    l = next[i-k];
    if ( l < m - i + 1 )
    {
      next[i] = l;
    }
    else 
    {
      j = (m-i+1 > 0 ? m-i+1 : 0);
      while (str[i+j] == str[0+j]) j ++;
      next[i] = j;
      k = i;
    }
  }
  next[0] = i;
}


int main()
{
  int T, t, n;
  scanf("%d", &T);
  for ( t = 1; t <= T; t++ )
  {
    scanf("%s", str);
    len = strlen( str );
    n = len;
    for ( int i = 0; i < len; i++ ) str[i+len] = str[i];
    str[len+len] = 0;
    //getNext();
    calnext();
    int up, i;
    for ( i = 1; i < len && i+next[i] < len; i++) ;
    up = n % i ? n : i;
    int l, e, g;
    l = e = g = 0;
    for ( i = 0; i < up; i++ )
    {
      if ( next[i] >= n ) e++;
      else if ( str[i+next[i]] > str[next[i]] ) g++;
      else l++;
    }
    printf("Case %d: %d %d %d\n", t, l, e, g);
  }
  return 0;
}
