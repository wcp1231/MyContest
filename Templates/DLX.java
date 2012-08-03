import java.util.Scanner;

public class DLX {
	
	static int logN = 3, N = logN * logN, NN = N * N, head, size;
	static int maxN = (N+1) * (N+1) * (N+1), maxM = 4 * (N+1) * (N+1);
	static int maxSize = maxN * maxM;
	static int[] R, L, U, D, CH, RH, S, O;
	
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		String temp;
		int T = inp.nextInt();
		for (int tt = 0; tt < T; tt++) {
			sudoku = new char[20][];
			for (int i = 1; i <= N; i++) {
				temp = inp.next();
				sudoku[i] = (" " + temp).toCharArray();
			}
			make();
			init(N * NN, 4 * NN);
			if (dfs(1)) {
				for (int i = 1; i <= len; ++i) {
					int x = (O[i] - 1) / NN + 1;
					O[i] -= (x - 1) * NN;
					int y = (O[i] - 1) / N + 1;
					O[i] -= (y - 1) * N;
					int z = O[i];
					sudoku[x][y] = (char) ('1' + z - 1);
				}
				for (int i = 1; i <= N; ++i) {
					for (int j = 1; j <= N; ++j) {
						System.out.print(sudoku[i][j]);
					}
					System.out.println();
				}
			} else System.out.println("impossible");
			if (tt < T-1) {
				System.out.println(inp.next());
			}
		}
	}
	
	public static int node(int up, int down, int left, int right) {
		U[size] = up; D[size] = down;
		L[size] = left; R[size] = right;
		D[up] = U[down] = R[left] = L[right] = size;
		return size++;
	}
	static boolean[][] mat;
	static void init(int n , int m) {
		L = new int[maxSize]; R = new int[maxSize];
		U = new int[maxSize]; D = new int[maxSize];
		CH = new int[maxSize]; RH = new int[maxSize];
		S = new int[maxM]; O = new int[maxM];
		size = 0;
		head = node(0, 0, 0, 0);
		for (int j = 1; j <= m; ++j) {
			CH[j] = node(size, size, L[head], head);
			S[j] = 0;
		}
		for (int i = 1; i <= n; ++i) {
			int row = -1, k;
			for (int j = 1; j <= m; j++) {
				if (!mat[i][j]) continue;
				if (row == -1) {
					row = node(U[CH[j]], CH[j], size, size);
					RH[row] = i;
					CH[row] = CH[j];
					++S[j];
				} else {
					k = node(U[CH[j]], CH[j], L[row], row);
					RH[k] = i;
					CH[k] = CH[j];
					++S[j];
				}
			}
		}
	}
	static void remove(int c) {
		L[R[c]] = L[c]; R[L[c]] = R[c];
		for (int i = D[c]; i != c; i = D[i]) {
			for (int j = R[i]; j != i; j = R[j]) {
				U[D[j]] = U[j]; D[U[j]] = D[j];
				--S[CH[j]];
			}
		}
	}
	static void resume(int c) {
		for (int i = U[c]; i != c; i = U[i]) {
			for (int j = L[i]; j != i; j = L[j]) {
				++S[CH[j]];
				U[D[j]] = D[U[j]] = j;
			}
		}
		L[R[c]] = R[L[c]] = c;
	}
	static int len;
	static boolean dfs(int k) {
		if (R[head] == head) {
			len = k - 1;
			return true;
		}
		int s = Integer.MAX_VALUE, c = 0;
		for (int t = R[head]; t != head; t = R[t]) {
			if (S[t] < s) {
				s = S[t];
				c = t;
			}
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
	static char[][] sudoku;
	static void make() {
		mat = new boolean[maxN][maxM];
		for (int i = 1; i <= N; ++i)
			for (int j = 1; j <= N; ++j)
				for (int k = 1; k <= N; ++k)
					if (sudoku[i][j] == '.' || sudoku[i][j] == '1' + k - 1)
						mat[(i - 1) * NN + (j - 1) * N + k][(i - 1) * N + j] = true;
		for (int i = 1; i <= N; ++i)
			for (int j = 1; j <= N; ++j)
				for (int k = 1; k <= N; ++k)
					if (sudoku[i][j] == '.' || sudoku[i][j] == '1' + k - 1)
						mat[(i - 1) * NN + (j - 1) * N + k][NN + (i - 1) * N + k] = true;
		for (int i = 1; i <= N; ++i)
			for (int j = 1; j <= N; ++j)
				for (int k = 1; k <= N; ++k)
					if (sudoku[i][j] == '.' || sudoku[i][j] == '1' + k - 1)
						mat[(i - 1) * NN + (j - 1) * N + k][2 * NN + (j - 1) * N + k] = true;
		int region;
		for (int i = 1; i <= N; ++i)
			for (int j = 1; j <= N; ++j)
				for (int k = 1; k <= N; ++k) {
					region = ((i - 1) / logN) * logN + (j - 1) / logN + 1;
					if (sudoku[i][j] == '.' || sudoku[i][j] == '1' + k - 1)
						mat[(i - 1) * NN + (j - 1) * N + k][3 * NN + (region - 1) * N + k] = true;
				}
	}
}
