#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <queue>
#define MAX 2550
#define INF 1 << 30

using namespace std;

int g[MAX][MAX],S,T;
queue <int> q;

bool bfs()
{
    int i,p;
    while(!q.empty())
        q.pop();
    for(i = 0;i < MAX;i++)
        dis[i] = -1;
    dis[S] = 0;
    q.push(S);
    while(!q.empty())
    {
        p = q.front();
        q.pop();
        for(i = 0;i <= T;i++)
        {
            if(dis[i] == -1 && g[p][i])
            {
                dis[i] = dis[p] + 1;
                q.push(i);
            }
        }
    }
    if(dis[T] == -1)
        return false;
    return true;
}

int getmin(int a,int b)
{
    return (a > b) ? b : a;
}

int dfs(int u,int f)
{
    int flow,i;
    if(u == T)
        return f;
    for(i = 0;i <= T;i++)
    {
        if(g[u][i] && dis[i] == dis[u]+1 && (flow = dfs(i,getmin(g[u][i],f)))
        {
            g[u][i] -= flow;
            g[i][u] += flow;
            return flow;
        }
    }
    return 0;
}

int main()
{
    //freopen("input.txt","a+",stdin);
    //freopen("output.txt","w",stdout);
    int d,ans;

    ans = 0;
    while(bfs())
    {
        while(d = dfs(S,INF))
        {
            ans += d;
        }
    }

    printf("%d\n",ans);
    return 0;
}
