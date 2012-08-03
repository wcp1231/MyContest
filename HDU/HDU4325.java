// 这题java和C++一样的写法，但超时
class HDU4325 {
    
    Scan sc;
    PrintStream ps;
    int n, m;
    Flower[] fl, fr;
    public void doit() {
        sc = new Scan( System.in );
        ps = System.out;
        int T = sc.nextInt();
        for ( int t = 1; t <= T; t++ ) {
            n = sc.nextInt();
            m = sc.nextInt();
            fl = new Flower[ n+2 ];
            fr = new Flower[ n+2 ];
            fl[ 0 ] = new Flower( -1, -1 );
            fr[ 0 ] = new Flower( -1, -1 );
            for ( int i = 1; i <= n; i++ ) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                fl[ i ] = new Flower( l, r );
                fr[ i ] = new Flower( l, r );
            }
            fl[ n+1 ] = new Flower( Integer.MAX_VALUE, Integer.MAX_VALUE);
            fr[ n+1 ] = new Flower( Integer.MAX_VALUE, Integer.MAX_VALUE);
            Arrays.sort(fl, new Comparator<Flower>() {
                @Override
                public int compare(Flower o1, Flower o2) {
                    return o1.l - o2.l;
                }
                
            });
            Arrays.sort( fr, new Comparator<Flower>() {
                @Override
                public int compare(Flower o1, Flower o2) {
                    return o1.r - o2.r;
                }
                
            });
            ps.println("Case #" + t + ":");
            for ( int i = 0; i < m; i++ ) {
                int q = sc.nextInt();
                int l = bsl( q );
                int r = bsr( q );
                int ans = l - r - 1;
                if ( fl[l].l == q ) ans ++;
                if ( fr[r].r == q) ans ++;
                ps.println( ans);
            }
        }
    }
    int[] ll = {-100, 1, 2, 3, 4, 6, 7, 100};
    int[] rr = {-100, 2, 7, 10,13, 19, 20, 100};
    public int bsl ( int k ) {
        int up = n+1;
        int low = 0;
        int mid = 0;
        int flag = Integer.MAX_VALUE;
        while ( low <= up ) {
            mid = ( up + low ) / 2;
            if ( mid >= n+1 ) return flag;
            if ( k == fl[ mid ].l ) {
                flag = mid;
                low = mid + 1;
            } else if ( k < fl[ mid ].l ) {
                flag = Math.min( up, flag );
                up = mid - 1;
            } else {
                low = mid + 1;
                flag = low;
            }
        }
        return flag;
    }
    public int bsr( int k ) {
        int up = n+1;
        int low = 0;
        int mid = 0;
        int flag = 0;
        while ( low <= up ) {
            mid = ( up + low ) / 2;
            if ( k == fr[ mid ].r ) {
                flag = mid;
                up = mid - 1;
            } else if ( k < fr[ mid ].r ) {
                up = mid - 1;
                flag = up;
            } else {
                flag = low;
                low = mid + 1;
            }
        }
        return flag;
    }
}
class Flower {
    int l, r;
    public Flower( int l, int r ) {
        this.l = l;
        this.r = r;
    }
}
class Scan {
    InputStream inp;
    
    public Scan(InputStream in) {
        inp = in;
    }
    public int nextInt() {
        int res = 0;
        int t;
        try {
            t = inp.read();
            if (t == -1) return -1;
            while (t != -1 && (t > '9' || t < '0')) t = inp.read();
            while (t != -1 && t >= '0' && t <= '9') {
                res = res * 10 + (t - '0');
                t = inp.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
