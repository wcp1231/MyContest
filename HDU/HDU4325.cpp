#include <cstdio>
#include <iostream>
#include <algorithm>

using namespace std;

struct Node 
{
  int l, r;
};

Node fl[100005], fr[100005];
int n, m;

bool cmp1(Node o1, Node o2)
{
  return o1.l < o2.l;
}
bool cmp2(Node o1, Node o2)
{
  return o1.r < o2.r;
}

int bsl( int k )
{
  int up = n+1;
  int low = 0;
  int mid = 0;
  int flag = 2000000000;
  while ( low <= up )
  {
    mid = ( up + low ) >> 1;
    if ( mid >= n+1 ) return flag;
    if ( k == fl[mid].l )
    {
      flag = mid;
      low = mid + 1;
    } else if ( k < fl[mid].l )
    {
      flag = min(flag, up);
      up = mid -1;
    } else 
    {
      low = mid + 1;
      flag = low;
    }
  }
  return flag;
}

int bsr( int k )
{
  int up = n+1, low = 0, mid = 0;
  int flag = 0;
  while ( low <= up )
  {
    mid = ( up + low ) >> 1;
    if ( k == fr[mid].r) 
    {
      flag = mid;
      up = mid - 1;
    } else if ( k < fr[mid].r) 
    {
      up = mid - 1;
      flag = up;
    } else 
    {
      flag = low;
      low = mid + 1;
    }
  }
  return flag;
}

int main()
{
  int T, t;
  scanf("%d", &T);
  for (t = 1; t <= T; t++)
  {
    scanf("%d %d", &n, &m);
    for ( int i = 1; i <= n; i++)
    {
      scanf("%d %d", &fl[i].l, &fl[i].r);
      fr[i].l = fl[i].l;
      fr[i].r = fl[i].r;
    }
    fl[n+1].l = fl[n+1].r = fr[n+1].l = fr[n+1].r = 2000000000;
    sort(fl, fl+n+2, cmp1);
    sort(fr, fr+n+2, cmp2);
    int q = 0;
    printf("Case #%d:\n", t);
    for ( int i = 0; i < m; i++)
    {
      scanf("%d", &q);
      int l = bsl( q );
      int r = bsr( q );
      int ans = l - r - 1;
      if ( fl[l].l == q ) ans ++;
      if ( fr[r].r == q ) ans ++;
      printf("%d\n", ans);
    }
  }
  return 0;
}
