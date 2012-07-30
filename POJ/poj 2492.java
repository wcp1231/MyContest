class UnionFindSet {
	int[] father, rank, sex;
	int len;
	public UnionFindSet(int n) {
		len = n + 1;
		father = new int[len];
		sex = new int[len];
		rank = new int[len];
		for (int i = 0; i < len; i++) {
			father[i] = i;
			sex[i] = rank[i] = 0;
		}
	}
	public int find(int x) {
		if (x == father[x]) return x;
		int a = find(father[x]);
		if (sex[x] == -1) sex[x] = (sex[father[x]] == -1 ? 1 : -1);
		else if (sex[x] == 1) sex[x] = (sex[father[x]] == -1 ? -1 : 1);
		return father[x] = a;
	}
	public boolean union(int x, int y) {
		int a = find(x);
		int b = find(y);
		if (a == b) {
			if (sex[x] != 0 && sex[y] != 0) {
				return (sex[x] != sex[y]); // 不是同类
			} else if (sex[x] == 0) {
				return sex[y] != 1; // 和根不同，可以合并
			} else if (sex[y] == 0) {
				return sex[x] != 1; // 和根不同
			}
		} else {
			if (rank[b] > rank[a]) {
				father[a] = father[x] = b;
				if (x != a) {
					int pre = sex[x];
					sex[x] = (sex[y] == -1 ? 1 : -1);
					if (sex[x] == -1) sex[a] = (pre == -1 ? 1 : -1);
					else sex[a] = (pre == -1 ? -1 : 1);
				} else {
					sex[a] = (sex[y] == -1 ? 1 : -1);
				}
			} else {
				father[b] = father[y] = a;
				if (rank[b] == rank[a]) rank[a]++;
				if (y != b) {
					int pre = sex[y];
					sex[y] = (sex[x] == -1 ? 1 : -1);
					if (sex[y] == -1) sex[b] = (pre == -1 ? 1 : -1);
					else sex[b] = (pre == -1 ? -1 : 1);
				} else {
					sex[b] = (sex[x] == -1 ? 1 : -1);
				}
			}
			return true;
		}
		return false;
	}
}
