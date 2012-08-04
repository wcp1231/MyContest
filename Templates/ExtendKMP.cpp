//扩展KMP算法，求的是字符串自身和自身的最长前缀
//这两个函数都是求自身和自身的。还没有求模式串和主串的。。。
int next[110];
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
