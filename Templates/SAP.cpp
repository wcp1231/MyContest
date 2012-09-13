/*
  似乎比 ISAP.cpp 更高效，那个 ISAP.cpp 没有 gap优化
*/
#define MAXN  100100
#define MAXM  400200
#define INT_MAX  999999999
struct Edge{
  int to, from, next, cap;
};

Edge e[MAXM];
int head[MAXN], eid, n, m, src, des;
int dep[MAXN], gap[MAXN];

void addEdge( int cu, int cv, int cw )
{
  e[ eid ].from = cu;
  e[ eid ].to = cv;
  e[ eid ].cap = cw;
  e[ eid ].next = head[ cu ];
  head[ cu ] = eid++;
  e[ eid ].from = cv;
  e[ eid ].to = cu;
  e[ eid ].cap = 0;
  e[ eid ].next = head[ cv ];
  head[ cv ] = eid++;
}

int que[MAXN];
void bfs()
{
  memset(dep, -1, sizeof(dep));
  memset(gap, 0, sizeof(gap));
  gap[ 0 ] = 1;
  int front = 0, rear = 0;
  dep[ des ] = 0;
  que[ rear++ ] = des;
  int u, v;
  while ( front != rear ) // 从汇点向原点 BFS
  {
    u = que[ front++ ];
    front = front % MAXN;
    for ( int i = head[u]; i != -1; i = e[i].next )
    {
      v = e[ i ].to;
      if ( e[i].cap != 0 || dep[v] != -1 )
        continue;
      que[ rear++ ] = v;
      rear = rear % MAXN;
      ++gap[ dep[v] = dep[u] + 1 ];
    }
  }
}
//int cur[MAXN]; // 去掉当前弧优化，这个优化似乎有争议
int stack[MAXN];
int sap()
{
  int res = 0, top = 0;
  bfs();
  //memcpy( cur, head, sizeof(head) );
  int u = src, i;
  while ( dep[ src ] < n )
  {
    if ( u == des )
    {
      int temp = INT_MAX, inser = n;
      for ( i = 0; i != top; ++i )
      {
        if ( temp > e[ stack[i] ].cap )
        {
          temp = e[ stack[i] ].cap;
          inser = i;
        }
      }
      for ( i = 0; i != top; ++i )
      {
        e[ stack[i] ].cap -= temp;
        e[ stack[i]^1 ].cap += temp;
      }
      res += temp;
      top = inser;
      u = e[ stack[top] ].from;
    }
    if ( u != des && gap[ dep[u]-1 ] == 0 )
      break;
    for ( i = head[u]; i != -1; i = e[i].next )
      if ( e[i].cap != 0 && dep[u] == dep[ e[i].to ]+1 )
        break;
    if ( i != -1 )
    {
      //cur[ u ] = i;
      stack[ top++ ] = i;
      u = e[ i ].to;
    } else 
    {
      int min = n;
      for ( i = head[u]; i != -1; i = e[i].next )
      {
        if ( e[i].cap == 0 )
          continue;
        if ( min > dep[ e[i].to ] )
        {
          min = dep[ e[i].to ];
          //cur[ u ] = i;
        }
      }
      --gap[ dep[u] ];
      ++gap[ dep[u] = min+1 ];
      if ( u != src )
        u = e[ stack[--top] ].from;
    }
  }
  return res;
}
