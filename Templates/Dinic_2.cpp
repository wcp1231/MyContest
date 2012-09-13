/*
  非递归版
 */
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#define NODE_SIZE 100010
#define EDGE_SIZE 400010
#define INF 0x7fffff

struct info{
    int s,e,f,next;
}edge[EDGE_SIZE];

int edge_num;
int head[NODE_SIZE];
int Que[NODE_SIZE];
int level[NODE_SIZE];
int cur_head[NODE_SIZE];

int edge_stack[4 * NODE_SIZE + 4 * EDGE_SIZE];
int stack_top;
int source,sink;

void add_edge(int s,int e,int f)
{
    int t = edge_num;
    edge[t].s = s;
    edge[t].e = e;
    edge[t].f = f;
    edge[t].next = head[s];
    head[s] = edge_num ++;
}

int BFS()
{
    int fr,tp,cur,next,e;
    memset(level,-1,sizeof(level));
    Que[0] = source;
    level[source] = 0;
    for( fr = 0,tp = 1; fr != tp; fr = (fr + 1)%NODE_SIZE ){
        cur = Que[fr];
        for( e = head[cur]; e != -1; e = edge[e].next ){
            next = edge[e].e;
            if( edge[e].f && level[next] == -1 ){
                Que[tp] = next;
                level[next] = level[cur] + 1;
                tp = (tp + 1)%NODE_SIZE;
            }
        }
    }
    return level[sink] != -1;
}

int find_max_flow()
{
    int cur,e,next;
    int i,index,min_limit;
    int res = 0;

    memcpy(cur_head,head,sizeof(head));                          
    cur = source;                                                   

    stack_top = 0;
    while( stack_top >= 0 ){            
        if( cur == sink ){                                       
            min_limit = INF;
            for( i = stack_top - 1; i >= 0 ; i -- ){
                e = edge_stack[i];
                if( edge[e].f <= min_limit ){
                    min_limit = edge[e].f;
                    index = i;
                }
            }
            res += min_limit;                                   

            for( i = stack_top - 1; i >= 0; i -- ){               
                e = edge_stack[i];
                edge[e].f -= min_limit;
                edge[e ^ 1].f += min_limit;
            }

            stack_top = index;                                    

            cur = edge[ edge_stack[ stack_top ] ].s;          
        }
        for( e = cur_head[cur]; e != -1; e = edge[e].next ){  
            cur_head[cur] = e;
            next = edge[e].e;
            if( edge[e].f && level[next] == level[cur] + 1 ){
                edge_stack[stack_top ++]  = e;
                cur = next;
                break;
            }
        }
        if( e == -1 ){                                           
            stack_top --;
            level[cur] = -2;                                    
            cur = edge[ edge_stack[stack_top] ].s;               
        }
    }
    return res;
}

int main()
{
    int t,i,n,m,a,b,c;
    int x,y;
    int res;
    scanf("%d",&t);
    while(t--)
    {
        scanf("%d%d",&n,&m);
        int min_x=INF,max_x=-INF;
        int min_id=1,max_id=1;
        for(i=1;i<=n;i++){
            scanf("%d%d",&x,&y);
            if(x<min_x){
                min_x = x;
                min_id = i;
            }
            if(x>max_x){
                max_x = x;
                max_id = i;
            }
        }

        source=min_id; sink=max_id;

        edge_num=0;
        memset(head,-1,sizeof(head));
        for(i=1;i<=m;i++){
            scanf("%d%d%d",&a,&b,&c);
            add_edge(a,b,c);
            add_edge(b,a,c);
        }

        res = 0;
        while( BFS() ){    
            res += find_max_flow();
        }
        printf("%d\n",res);
    }
    return 0;
}
