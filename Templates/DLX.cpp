¾«È·¸²¸Ç£º
// pku3076.cpp   
#include <cstdio>   
#include <cstring>   
#include <cmath>   
#include <cstdlib>   
#include <iostream>   
#include <algorithm>   
#include <vector>   
#include <map>   
#include <set>   
#define out(v) cout << #v << ": " << (v) << endl   
using namespace std;   
typedef long long LL;
  // maxN 行   maxM 列
const int maxN = 20 * 20 * 20, maxM = 4 * 20 * 20, N = 16, NN = N * N, n = 4;   
const int max_size = maxN * maxM;   
const int inf = 0x3f3f3f3f;   
int L[max_size], R[max_size], U[max_size], D[max_size], CH[max_size], RH[max_size];   
int S[maxM], O[maxM];   
int head, size;   
  
int node(int up, int down, int left, int right) {   
    U[size] = up, D[size] = down;   
    L[size] = left, R[size] = right;   
    D[up] = U[down] = R[left] = L[right] = size;   
    return size++;   
}   
bool mat[maxN][maxM];   
void init(int N, int M) {   
    size = 0;   
    head = node(0, 0, 0, 0);   
    for (int j = 1; j <= M; ++j) {   
        CH[j] = node(size, size, L[head], head), S[j] = 0;   
    }   
    for (int i = 1; i <= N; ++i) {   
        int row = -1, k;   
        for (int j = 1; j <= M; ++j) {   
            if (!mat[i][j]) continue;   
            if (row == -1) {   
                row = node(U[CH[j]], CH[j], size, size);   
                RH[row] = i, CH[row] = CH[j], ++S[j];   
            } else {   
                k = node(U[CH[j]], CH[j], L[row], row);   
                RH[k] = i, CH[k] = CH[j], ++S[j];   
            }   
        }   
    }   
}   
  
void remove(const int &c) {   
    L[R[c]] = L[c], R[L[c]] = R[c];   
    for (int i = D[c]; i != c; i = D[i]) {   
        for (int j = R[i]; j != i; j = R[j]) {   
            U[D[j]] = U[j], D[U[j]] = D[j];   
            --S[CH[j]];   
        }   
    }   
}   
void resume(const int &c) {   
    for (int i = U[c]; i != c; i = U[i]) {   
        for (int j = L[i]; j != i; j = L[j]) {   
            ++S[CH[j]];   
            U[D[j]] = D[U[j]] = j;   
        }   
    }   
    L[R[c]] = R[L[c]] = c;   
}   
int len;   
bool dfs(const int &k) {   
    if (R[head] == head) {   
        len = k - 1;   
        return true;   
    }   
    int s = inf, c;   
    for (int t = R[head]; t != head; t = R[t]) {   
        if (S[t] < s) s = S[t], c = t;   
    }   
    remove(c);   
    for (int i = D[c]; i != c; i = D[i]) {   
        O[k] = RH[i];   
        for (int j = R[i]; j != i; j = R[j]) {   
            remove(CH[j]);   
        }   
        if (dfs(k + 1)) {   
            return true;   
        }   
        for (int j = L[i]; j != i; j = L[j]) {   
            resume(CH[j]);   
        }   
    }   
    resume(c);   
    return false;   
}   
  
char sudoku[20][20];   
void make()   
{   
    memset(mat, false, sizeof(mat));   
    const int N = 16, NN = N * N, n = 4;   
    for (int i = 1; i <= N; ++i)   
        for (int j = 1; j <= N; ++j)   
            for (int k = 1; k <= N; ++k)   
                if (sudoku[i][j] == '-' || sudoku[i][j] == 'A' + k - 1)   
                    mat[(i - 1) * NN + (j - 1) * N + k][(i - 1) * N + j] = true;   
    for (int i = 1; i <= N; ++i)   
        for (int k = 1; k <= N; ++k)   
            for (int j = 1; j <= N; ++j)   
                if (sudoku[i][j] == '-' || sudoku[i][j] == 'A' + k - 1)   
                    mat[(i - 1) * NN + (j - 1) * N + k][NN + (i - 1) * N + k] = true;   
    for (int j = 1; j <= N; ++j)   
        for (int k = 1; k <= N; ++k)   
            for (int i = 1; i <= N; ++i)   
                if (sudoku[i][j] == '-' || sudoku[i][j] == 'A' + k - 1)   
                    mat[(i - 1) * NN + (j - 1) * N + k][NN * 2 + (j - 1) * N + k] = true;   
    int region;   
    for (int i = 1; i <= N; ++i)   
        for (int j = 1; j <= N; ++j)   
            for (int k = 1; k <= N; ++k) {   
                region = ((i - 1) / n) * n + (j - 1) / n + 1;   
                if (sudoku[i][j] == '-' || sudoku[i][j] == 'A' + k - 1)   
                    mat[(i - 1) * NN + (j - 1) * N + k][NN * 3 + (region - 1) * N + k] = true;   
            }   
}   
  
int main()   
{   
    bool first = true;   
    while (scanf("%s", sudoku[1] + 1) != EOF)   
    {   
        for (int i = 2; i <= N; ++i)   
            scanf("%s", sudoku[i] + 1);   
        make();   
        init(N * N * N, 4 * N * N);   
        dfs(1);   
        for (int i = 1; i <= len; ++i) {   
            int x = (O[i] - 1) / NN + 1;   
            O[i] -= (x - 1) * NN;   
            int y = (O[i] - 1) / N + 1;   
            O[i] -= (y - 1) * N;   
            int z = O[i];   
            sudoku[x][y] = 'A' + z - 1;   
        }   
        if (first) first = false;   
        else {   
            putchar('\n');   
        }   
        for (int i = 1; i <= N; ++i) {   
            for (int j = 1; j <= N; ++j)   
                putchar(sudoku[i][j]);   
            putchar('\n');   
        }   
    }   
    return 0;   
}  
¶àÖØ¸²¸Ç
#include <cstdio>   
#include <cstring>   
#include <cmath>   
#include <cstdlib>   
#include <ctime>   
#include <iostream>   
#include <algorithm>   
#include <vector>   
#include <map>   
#include <set>   
#define out(v) cout << #v << ": " << (v) << endl   
using namespace std;   
typedef long long LL;   
  
const int max_size = 60 * 60;   
const int maxint = 0x3f3f3f3f;   
int L[max_size], R[max_size], U[max_size], D[max_size], CH[max_size];   
int S[60];   
int head, size;   
  
void remove(const int &c) {   
    for (int i = D[c]; i != c; i = D[i]) {   
        L[R[i]] = L[i], R[L[i]] = R[i];   
        --S[CH[i]];   
    }   
}   
void resume(const int &c) {   
    for (int i = U[c]; i != c; i = U[i]) {   
        ++S[CH[i]];   
        L[R[i]] = R[L[i]] = i;   
    }   
}   
int H() {   
    int cnt = 0;   
    bool cover[60] = {false};   
    for (int c = R[head]; c != head; c = R[c])   
        if (!cover[c]) {   
            cover[c] = true, ++cnt;   
            for (int i = D[c]; i != c; i = D[i])   
                for (int j = R[i]; j != i; j = R[j])   
                    cover[CH[j]] = true;   
        }   
    return cnt;   
}   
int ans, ans_cnt;   
void dfs(const int &k) {   
    if (R[head] == head) {   
        ans = min(ans, k);   
        return;   
    }   
    int s(maxint), c;   
    for (int t = R[head]; t != head; t = R[t]) {   
        if (S[t] < s) s = S[t], c = t;   
    }   
    for (int i = D[c]; i != c; i = D[i]) {   
        remove(i);   
        for (int j = R[i]; j != i; j = R[j])   
            remove(j);   
        if (k + 1 + H() < ans)   
            dfs(k + 1);   
        for (int j = L[i]; j != i; j = L[j])   
            resume(j);   
        resume(i);   
    }   
}   
int node(int up, int down, int left, int right) {   
    U[size] = up, D[size] = down;   
    L[size] = left, R[size] = right;   
    D[up] = U[down] = R[left] = L[right] = size;   
    return size++;   
}   
  
bool G[60][60];   
void init(int N, int M) {   
    size = 0;   
    head = node(0, 0, 0, 0);   
    for (int j = 1; j <= M; ++j) {   
        node(j, j, L[head], head);   
        CH[j] = j, S[j] = 0;   
    }   
    int u, v;   
    for (int u = 1; u <= N; ++u) {   
        int row = -1;   
        for (int v = 1; v <= M; ++v) {   
            if (!G[u][v]) continue;   
            if (row == -1) {   
                row = node(U[CH[v]], CH[v], size, size);   
                CH[row] = v, ++S[v];   
            } else {   
                int k = node(U[CH[v]], CH[v], L[row], row);   
                CH[k] = v, ++S[v];   
            }   
        }   
    }   
}   
  
int main()   
{   
    int N, M, u, v;   
    while (scanf("%d%d", &N, &M) != EOF)   
    {   
        for (int i = 1; i <= N; ++i)   
            for (int j = 1; j <= N; ++j)   
                G[i][j] = (i == j ? true : false);   
        while (M--) {   
            scanf("%d%d", &u, &v);   
            G[u][v] = G[v][u] = true;   
        }   
        init(N, N);   
        ans = maxint;   
        dfs(1);   
        printf("%d\n", ans - 1);   
    }   
    return 0;   
} 
