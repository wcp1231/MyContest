/*
  题目同POJ1466,另一种写法
  这种写法相当于建立双向边。。。
  4
  0: (2) 1 3
  1: (2) 0 2
  2: (2) 1 3
  3: (2) 0 2
  的最大匹配为4, 答案是 n - 4/2
 */
#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

const int MAXN = 505;
bool gmap[MAXN][MAXN], visted[MAXN];
int match[MAXN], n;

bool dfs( int p )
{
  for ( int i = 0; i < n; i++ )
  {
    if ( gmap[p][i] && !visted[i] )
    {
      visted[i] = true;
      int temp = match[i];
      match[i] = p;
      if ( temp == -1 || dfs(temp) ) return true;
      match[i] = temp;
    }
  }
  return false;
}
int mat()
{
  int ans = 0;
  for ( int i = 0; i < n; i++ )
  {
    memset(visted, false, sizeof(visted));
    if ( dfs(i) ) ans++;
  }
  return ans;
}
int main()
{
  while ( scanf("%d", &n) != EOF )
  {
    int p, m, a;
    memset( gmap, false, sizeof(gmap) );
    memset( match, -1, sizeof(match) );
    for ( int i = 0; i < n; i++ )
    {
      scanf("%d: (%d)", &p, &m );
      for ( int j = 0; j < m; j++ )
      {
        scanf("%d", &a);
        gmap[p][a] = true;
      }
    }
    printf( "%d\n", n-mat()/2 );
  }
  return 0;
}
