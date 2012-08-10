/*
  题目同HDU1068,另一种写法
  这里的做法是拆点，将一个点拆成两个
  4
  0: (2) 1 3
  1: (2) 0 2
  2: (2) 1 3
  3: (2) 0 2
  相当于有8个点，最大匹配是4, 答案是 (8 - 4) / 2
 */
#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

const int MAXN = 1005;
bool gmap[MAXN][MAXN], visted[MAXN];
int match[MAXN], n;

bool dfs( int p )
{
  for ( int i = 0; i < p; i++ )
  {
    if ( gmap[p][i] && !visted[i] )
    {
      visted[i] = true;
      int temp = match[i];
      match[i] = p;
      if ( !temp || dfs(temp) ) return true;
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
    memset( match, 0, sizeof(match) );
    for ( int i = 0; i < n; i++ )
    {
      scanf("%d: (%d)", &p, &m );
      for ( int j = 0; j < m; j++ )
      {
        scanf("%d", &a);
        gmap[p][n+a] = gmap[n+p][a] = true;
      }
    }
    n *= 2;
    printf( "%d\n", (n-mat())/2 );
  }
  return 0;
}

