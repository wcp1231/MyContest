// KMP的应用，每行求出寻环节，然后取各行里共有的最小值
// 然后竖着再用一次KMP求高度
// 最后一段的kmp和为改进的kmp有点类似。
// abcabc 的next数组
// 最后一段的结果是  -1 -1 -1 0 1 2
// 为改进的kmp结果是 -1  0  0 0 1 2
// 区别在于在第一个循环节的位置是 全-1 还是 -1然后00
#include <cstdio>
#include <iostream>
#include <cstring>

using namespace std;

int next[10005], r, c, num[80], len, w, h;
char t[80], s[10005][80];
int main()
{
  scanf("%d %d", &r, &c);
  memset(num, 0, sizeof(num));
  for ( int i = 0; i < r; i++ )
  {
    scanf("%s", t);
    strcpy( s[i], t );
    int p;
    for ( int j = c; j > 0; j-- )
    {
      int x, y;
      t[j] = 0;
      for ( x = 0, y = 0; s[i][y]; x++, y++ ) 
      {
        if ( !t[x] ) x = 0;
        if ( t[x] != s[i][y] ) break;
      }
      if ( !s[i][y] ) num[j]++;
    }
  }
  for ( int i = 1; i <= c; i++ )
    if ( num[i] == r )
    {
      w = i;
      break;
    }
  for ( int i = 0; i < r; i++ ) s[i][w] = 0;
  next[0] = -1;
  for ( int i = 1, j = -1; i < r; i++ ) 
  {
    while ( j != -1 && strcmp( s[j+1], s[i] ) ) j = next[j];
    if ( !strcmp(s[j+1], s[i]) ) j++;
    next[i] = j;
  }
  printf("%d\n", w * (r-1-next[r-1]));
  return 0;
}

