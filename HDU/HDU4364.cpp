#include <cstdio>

int mat[4][4], ans[4][4], AES[4][4] = {{2, 3, 1, 1}, {1, 2, 3, 1}, {1, 1, 2, 3}, {3, 1, 1, 2}};

int opt( int x, int y )
{
  int res = 0;
  for( int i = 0; i < 4; i++ )
  {
    int o = AES[ x ][ i ], temp = 0;
    if ( 1 == o ) temp = mat[ i ][ y ];
    else if ( 2 == o )
    {
      temp = mat[ i ][ y ] << 1;
      if ( temp > 0xFF ) temp ^= 0x1B;
    } else if ( 3 == o )
    {
      temp = mat[ i ][ y ] << 1;
      if ( temp > 0xFF ) temp ^= 0x1B;
      temp ^= mat[ i ][ y ];
    }
    res ^= temp;
  }
  return res & 255;
}
int main()
{
  int T;
  scanf("%d", &T);
  for ( int t = 0; t < T; t++ )
  {
    if ( t ) printf("\n");
    for ( int i = 0; i < 4; i++ )
      for ( int j = 0; j < 4; j++ )
        scanf( "%x", &mat[ i ][ j ] );
    for ( int i = 0; i < 4; i++ )
      for ( int j = 0; j < 4; j++ )
        ans[ i ][ j ] = opt( i, j );
    for ( int i = 0; i < 4; i++ )
    {
      for ( int j = 0; j < 4; j++ )
      {
        if ( j ) printf( " " );
        printf( "%02X", ans[ i ][ j ] );
      }
      printf( "\n" );
    }
  }
  return 0;
}
