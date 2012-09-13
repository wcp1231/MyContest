/*
  比赛的时候爆栈了，最后发现只要开挂了就好。代码是 Dinic 的多路增广
  如此开挂：
  #pragma comment(linker, "/STACK:1024000000,1024000000")
  因为 HDU 的服务器是 Window 的，比较坑。。。
 */
#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#pragma comment(linker, "/STACK:1024000000,1024000000")

using namespace std;
#define MAXN  100100
#define MAXM  200200
#define inf 999999999
#define null = -1;

struct Edge
{
    int adj,next,re;    //指向的点，下一边的下标，逆边的下标
    int r;          //余留网边的容量
};  //用下标模拟指针构邻接表

Edge h[MAXM+10];
int p[MAXN+10],c;            //各点的头指针，目前h数组开到的位置
int Q[MAXN+10],level[MAXN+10];
int s, t, n, m;

//插边，k,l为端点，cap为边容量，d为是否双向
void insert(int k,int l,int cap,bool d)
{
    h[++c].adj=l;
    h[c].r=cap;
    h[c].next=p[k];
    p[k]=c;
    h[c].re=c+1;    //****解决逆边问题的技巧，多存一个逆边在h中的位置****

    //逆边
    h[++c].adj=k;
    int a=d ? cap : 0;
    h[c].r=a;
    h[c].next=p[l];
    p[l]=c;
    h[c].re=c-1;    //与上面对应

}

//广搜求层次图
bool bfs()
{
    memset(level,-1,sizeof(level));
    int head=0,tail=0;
    Q[tail++]=s;
    level[s]=0;
    while (head<tail)
    {
        int v=Q[head++];
        for ( int i = p[v]; i != -1; i = h[i].next )
        {
            int j=h[i].adj;
            if (level[j]==-1 && h[i].r)
            {
                level[j]=level[v]+1;
                Q[tail++]=j;
            }
        }
    }
    return (level[t]!=-1);
}

//深搜扩充流
int dfs(int v,int c)
{
    if (v==t) return c;  //遇汇点结束
    //int sum=c;
    int flow, w = 0;
    for ( int i = p[v]; i != -1 && c-w > 0; i = h[i].next )
    {
        int j=h[i].adj;
        if (h[i].r && level[j]==level[v]+1)
        {
                flow=dfs(j,min(c-w,h[i].r));  //递归确定本次要扩充的流大小
                if ( flow == 0 ) level[j] = -1;
                else {
                    w += flow;
                    h[i].r -= flow;
                    h[i].re += flow;
                }
        }
    }
    return w;
}
//dinic算法主体，不断求层次图并增流，直至汇点不可达
int dinic()
{
    int ans=0;
    while (bfs())
        ans+=dfs(s,inf);
    return ans;
}

//初始化
void init()
{
    c = -1;
    memset(p, -1, sizeof(p));
}

int main() {
    int T;
    int sx, sid, ex, eid, x, y, c;
    scanf("%d", &T);
    while( T-- ) {
        scanf("%d %d", &n, &m);
        sx = 100001;
        ex = -100001;
        for ( int i = 0; i < n; i++ ) {
            scanf("%d %d", &x, &y);
            if ( x < sx ) {
                sx = x;
                sid = i+1;
            }
            if ( x > ex ) {
                ex = x;
                eid = i+1;
            }
        }
        init();
        for ( int i = 0; i < m; i++ ) {
            scanf("%d %d %d", &x, &y, &c);
            insert( x, y, c, true );
        }
        s = sid;
        t = eid;
        int ans = dinic();
        printf("%d\n", ans);
    }
    return 0;
}
