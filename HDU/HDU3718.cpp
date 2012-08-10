/*
  二部图的最佳匹配，用的是KM算法
  一直 ACCESS_VIOLATION 的原因是我这样读取 scanf("%c%c", &ch, &inp[i]);
  之后改成这样就 Accept 了。。scanf("%s", ch); inp[i] = ch[0];
*/
#include <cstdio>
#include <cstring>
using namespace std;

const int maxn=30,OO=2147483647;
int w[maxn][maxn];
int lx[maxn],ly[maxn];
int linky[maxn];
int visx[maxn],visy[maxn];
int N, m, len;
int slack[maxn];
char ans[20005], inp[20005];

void input(){
  char ch[3];
  for ( int i = 0; i < len; i++ )
  {
    scanf("%s", ch);
    inp[i] = ch[0];
  }
  inp[len] = 0;
  memset(w, 0, sizeof(w));
  for ( int i = 0; i < len; i++ )
  {
    w[ans[i]-'A'][inp[i]-'A']++;
  }
}
bool find(int x){
	visx[x]=true;
	for(int y=0;y<N;++y){
		if(visy[y])continue;
		int t=lx[x]+ly[y]-w[x][y];
		if(t==0){
			visy[y]=true;
			if(linky[y]==-1||find(linky[y])){
				linky[y]=x;
				return true;
			}
		}
		else{
			if(slack[y]>t)
				slack[y]=t;
		}
	}
	return false;
}
void KM(){
	memset(linky,-1,sizeof(linky));
	memset(lx,0,sizeof(lx));
	memset(ly,0,sizeof(ly));
	for(int i=0;i<N;++i)
		for(int j=0;j<N;++j)
			if(w[i][j]>lx[i])
				lx[i]=w[i][j];
	for(int x=0;x<N;++x){
		for(int i=0;i<N;++i)
			slack[i]=OO;
		for(;;){
			memset(visx,0,sizeof(visx));
			memset(visy,0,sizeof(visy));
			if(find(x))break;
			int d=OO;
			for(int i=0;i<N;++i){
				if(!visy[i])
					if(d>slack[i])
						d=slack[i];
			}
			for(int i=0;i<N;++i){
				if(visx[i])
					lx[i]-=d;
			}
			for(int i=0;i<N;++i){
				if(visy[i])
					ly[i]+=d;
				else
					slack[i]-=d;
			}
		}
	}
}
void output(){
	int res=0;
	for(int j=0;j<N;++j){
		for(int i=0;i<N;++i)
			res+=w[i][j];
        res-=w[linky[j]][j];
	}
    int cnt = len - res;
	printf("%.4lf\n", (cnt*1.0)/len);
}
int main(){
  int T;
  char ch[3];
  scanf("%d", &T);
  while ( T-- )
  {
    scanf("%d %d %d",&len, &N, &m);
    N = 26;
    for ( int i = 0; i < len; i++ )
    {
      scanf("%s", ch);
      ans[i] = ch[0];
    }
    ans[len] = 0;
    for ( int i = 0; i < m; i++ )
    {
      input();
      KM();
      output();
    }
  }
  return 0;
}
