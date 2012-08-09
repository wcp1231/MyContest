#include <iostream>
#include <cstdio>
#include <cstring>

using namespace std;

typedef  struct {
	int v, next, val;
} edge;
const int MAXN = 50010;
const int MAXM = 400010;
const int INF = 90000000;
edge e[MAXM];
int p[MAXN], eid;
inline void init() {
	memset(p, -1, sizeof(p));
	eid = 0;
}
//有向
inline void insert1(int from, int to, int val) {
	e[eid].v = to;
	e[eid].val = val;
	e[eid].next = p[from];
	p[from] = eid++;
	swap(from, to);
	e[eid].v = to;
	e[eid].val = 0;
	e[eid].next = p[from];
	p[from] = eid++;
}
//无向
inline void insert2(int from, int to, int val) {
	e[eid].v = to;
	e[eid].val = val;
	e[eid].next = p[from];
	p[from] = eid++;
	swap(from, to);
	e[eid].v = to;
	e[eid].val = val;
	e[eid].next = p[from];
	p[from] = eid++;
}
int n, m; //n为点数 m为边数
int h[MAXN];
int gap[MAXN];
int source, sink;
inline int dfs(int pos, int cost) {
	if (pos == sink) {
		return cost;
	}
	int j, minh = n - 1, lv = cost, d;
	for (j = p[pos]; j != -1; j = e[j].next) {
		int v = e[j].v, val = e[j].val;
		if(val > 0) {
			if (h[v] + 1 == h[pos]) {
				if (lv < e[j].val) {
					d = lv;
				} else {
					d = e[j].val;
				}
				d = dfs(v, d);
				e[j].val -= d;
				e[j ^ 1].val += d;
				lv -= d;
				if (h[source] >= n) {
					return cost - lv;
				}
				if (lv == 0) {
					break;
				}
			}
			if (h[v] < minh)	{
				minh = h[v];
			}
		}
	}
	if (lv == cost) {
		--gap[h[pos]];
		if (gap[h[pos]] == 0) {
			h[source] = n;
		}
		h[pos] = minh + 1;
		++gap[h[pos]];
	}
	return cost - lv;
}
int sap(int st, int ed) {
	source = st;
	sink = ed;
	int ret = 0;
	memset(gap, 0, sizeof(gap));
	memset(h, 0, sizeof(h));
	//gap[st] = n;
	gap[0] = n;
	while (h[st] < n) {
		ret += dfs(st, INF);
	}
	return ret;
}
int gm[202][202];
int main()
{
  int r, c, cnt = 1;
  while ( scanf("%d %d", &r, &c) != EOF )
  {
    n = r * c+2;
    for ( int i = 0; i < r; i++ )
      for ( int j = 0; j < c; j++ ) scanf("%d", &gm[i][j]);
    init();
    for ( int i = 0; i < r; i++ )
    {
      for ( int j = 0; j < c; j++ )
      {
        if ( gm[i][j] == 0 )
        {
          if ( j < c-1 )
          {
            insert2( i*c+j+1, i*c+j+2, 1 );
          }
          if ( i < r-1 )
          {
            insert2( i*c+j+1, (i+1)*c+j+1, 1 );
          }
        } else if ( gm[i][j] == 1 )
        {
          insert1( i*c+j+1, n-1, INF );
          if ( j < c-1 && gm[i][j+1] != 1 )
            insert2( i*c+j+1, i*c+j+2, 1 );
          if ( i < r-1 && gm[i+1][j] != 1 )
            insert2( i*c+j+1, (i+1)*c+j+1, 1 );
        } else if ( gm[i][j] == 2 )
        {
          insert1( 0, i*c+j+1, INF );
          if ( j < c-1 && gm[i][j+1] != 2 )
            insert2( i*c+j+1, i*c+j+2, 1);
          if ( i < r-1 && gm[i+1][j] != 2 )
            insert2( i*c+j+1, (i+1)*c+j+1, 1 );
        }
      }
    }
    printf("Case %d:\n%d\n", cnt++, sap(0, n-1));
  }
  return 0;
}

