// 改进优化版 KMP
void getNext( char *t )
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

// 未改进版KMP
void calnext( char *t )
{
  int lent = strlen( t );
  next[ 0 ] = -1;
  int i = 0, ct = next[ 0 ];
  while ( i < lent-1 )
  {
    if ( ct < 0 || t[ i ] == t[ ct ] )
      next[ ++i ] = ++ct;
    else ct = next[ ct ];
  }
}

// 匹配
int kmp( char *s, char *t )
{
  int lens = strlen( s );
  int lent = strlen( t );
  // getNext( t ) || calnext( t );
  int i = 0, j = 0;
  while ( i < lens && j < lent)
  {
    if ( j < 0 || s[ i ] == t[ j ] )
    {
      ++j, ++j;
    }
    else j = next[ j ];
    if ( j == lent ) return i - j;
  }
  return -1;
}
