#include <cstdio>
#include <iostream>
#include <cstring>

using namespace std;

int ans[ 155 ], cnt, S, T;
int map[ 155 ][ 155 ], n, m;
bool used[ 155 ];

void reverse( int s, int t )
{
  int a = s;
  int b = t;
  while ( a < b )
  {
    int temp = ans[ a ];
    ans[ a ] = ans[ b ];
    ans[ b ] = temp;
    a ++;
    b --;
  }
}

int main()
{
  freopen( "1007.in", "r", stdin );
  while( scanf( "%d %d", &n, &m ) != EOF )
  {
    int a, b;
    memset( map, 0, sizeof( map ) );
    for ( int i = 0; i < m; i++ )
    {
      scanf( "%d %d", &a, &b );
      map[ a ][ b ] = map[ b ][ a ] = 1;
    }
    memset( used, false, sizeof( used ) );
    memset( ans, 0, sizeof( ans ) );
    cnt = 0;
    S = 1;
    ans[ cnt++ ] = S;
    used[ S ] = true;
    for ( T = 2; T <= n; T++ ) if ( map[ S ][ T ] ) break;
    ans[ cnt++ ] = T;
    used[ T ] = true;
    int p = 1;
    while ( cnt < n )
    {
      bool flag = true;
      while ( flag )
      {
        flag = false;
        for ( int i = 1; i <= n; i++ )
          if ( !used[ i ] && map[ T ][ i ] )
          {
            ans[ cnt++ ] = i;
            used[ i ] = true;
            T = i;
            flag = true;
          }
      }
      if ( cnt < n )
      {
         for ( int i = p; i < cnt-1; i++ )
          if ( map[ S ][ ans[i+1] ] && map[ ans[i] ][ T ] )
          {
            reverse( i+1, cnt-1 );
            T = ans[ cnt-1 ];
            p ++;
            if ( p >= cnt-1 ) p = 1;
            break;
          }
      }
      if ( !map[ S ][ T ] )
      {
        for ( int i = p; i < cnt-1; i++ )
          if ( map[ S ][ ans[i+1] ] && map[ ans[i] ][ T ] )
          {
            reverse( i+1, cnt-1 );
            T = ans[ cnt-1 ];
            p ++;
            if ( p >= cnt-1 ) p = 1;
            break;
          }
      }
    }
    printf( "%d", ans[ 0 ] );
    for ( int i = 1; i < cnt; i++ ) printf( " %d", ans[ i ] );
    puts( "" );
  }
  return 0;
}
