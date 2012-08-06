#include <cstdio>
#include <iostream>
#include <cstring>

using namespace std;

char str[220005], temp[110005];
int n, p[220005];

int getMin( int a, int b )
{
  return a < b ? a : b;
}
int getMax( int a, int b )
{
  return a > b ? a : b;
}
void kp()
{
  int mx = 0, id;
  for ( int i = 1; i < n; i++ )
  {
    if ( mx > i ) p[i] = getMin( p[2*id-i], p[id]+id-i );
    else p[i] = 1;
    for (; str[i+p[i]] == str[i-p[i]]; p[i]++) ;
    if ( p[i] + i > mx )
    {
      mx = p[i] + i;
      id = i;
    }
  }
}
int main()
{
  while ( scanf("%s", temp) != EOF )
  {
    str[0] = '$'; // 字符串从1开始
    int idx = 1;
    for ( int i = 0; temp[i]; i++ )
    {
      str[idx++] = '#';
      str[idx++] = temp[i];
    }
    str[idx++] = '#';
    str[idx] = 0;
    // 以上将字符串扩展为#X#X#形式
    n = strlen( str );
    kp();// 求 p 数组
    int ans = 0;
    for ( int i = 0; i < n; i++ ) ans = getMax( ans, p[i] );
    printf( "%d\n", ans-1 );
  }
  return 0;
}
