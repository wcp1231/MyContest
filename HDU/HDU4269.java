/*
  长春网络赛某题，纯模拟。输入和字符串处理比较蛋疼
 */
class HDU4269 {
	Scanner sc;
	PrintStream ps;
	int money, grids;
	public void doit() throws FileNotFoundException {
		sc = new Scanner( System.in );
		ps = System.out;
		TreeMap<String, Equipment> map = new TreeMap<String, Equipment>();
		TreeMap<String, Integer> list = new TreeMap<String, Integer>();
		int T = 1;
		while ( sc.hasNext() ) {
			map.clear();
			list.clear();
			int n = sc.nextInt();
			for ( int i = 0; i < n; i++ ) {
				String name = sc.next();
				int num = sc.nextInt();
				Equipment normal = new Equipment( name, num, 0 );
				map.put( name, normal );
			}
			n = sc.nextInt();
			sc.nextLine();
			for ( int i = 0; i < n; i++ ) {
				String[] inp = sc.nextLine().split("[:]");
				String[] t = inp[0].split(" ");
				Equipment mixture = new Equipment( t[0], Integer.parseInt(t[1]), 1 );
				mixture.setRecipe( inp[1].split(",") );
				int sum = 0;
				map.put( t[0], mixture );
			}
			n = sc.nextInt();
			for ( int i = 0; i < n; i++ ) {
				String name = sc.next();
				int num = sc.nextInt();
				Equipment consume = new Equipment( name, num, 2 );
				map.put( name, consume );
			}
			money = 0;
			grids = 6;
			n = sc.nextInt();
			for ( int i = 0; i < n; i++ ) {
				String cmd = sc.next();
				if ( cmd.charAt(0) == '+' ) {
					if ( cmd.matches("\\+[0-9]+") ) {
						money += Integer.parseInt( cmd.substring(1) );
					} else {
						String name = cmd.substring(1);
						if ( map.containsKey(name)) {
							Equipment eq = map.get(name);
							if ( eq.type == 0 && grids > 0 && money >= eq.cost ) {
								int num = 0;
								if ( list.containsKey(eq.name) ) num = list.get(eq.name);
								num++;
								list.put( eq.name, num );
								money -= eq.cost;
								grids--;
							} else if ( eq.type == 1 && money >= eq.cost ) {
								boolean flag = true;
								for ( int j = 0; j < eq.len; j++ ) {
									if (!list.containsKey(eq.recName[j]) || list.get(eq.recName[j]) < eq.recNum[j]) {
										flag = false;
									}
								}
								if ( flag ) {
									int add = 0, sum = 0;
									for ( int j = 0; j < eq.len; j++ ) {
										int num = list.get(eq.recName[j]);
										add += num;
										sum += map.get(eq.recName[j]).sumCost * num;
										list.remove(eq.recName[j]);
									}
									Equipment temp = map.get(eq.name);
									temp.sumCost = sum + temp.cost;
									
									grids += add;
									int num = 0;
									if ( list.containsKey(eq.name) ) num = list.get(eq.name);
									num++;
									list.put( eq.name, num );
									money -= eq.cost;
									grids--;
								}
							} else if ( eq.type == 2 && grids > 0 && money >= eq.cost ) {
								int num = 0;
								boolean flag = true;
								if ( list.containsKey(eq.name) ) {
									num = list.get(eq.name);
									flag = false;
								}
								num++;
								list.put( eq.name, num );
								money -= eq.cost;
								if ( flag ) grids--;
							}
						}
					}
				} else {
					String name = cmd.substring(1);
					if ( list.containsKey(name) ) {
						Equipment temp = map.get( name );
						if ( temp.type != 2 ) {
							int num = list.get(name);
							if ( num > 0 ) {
								list.put( name, num-1 );
								money += temp.sumCost;
								grids++;
							} else list.remove(name);
						} else {
							money += temp.sumCost * list.get(name);
							list.remove(name);
							grids++;
						}
					}
				}
			}
			ps.println("Case " + T + ":");
			T++;
			ps.println( money );
			ps.println( 6 - grids );
			Iterator<Entry<String, Integer>> it = list.entrySet().iterator();
			while ( it.hasNext() ) {
				Map.Entry<String, Integer> e = (Map.Entry<String, Integer>) it.next();
				if ( map.get(e.getKey()).type != 2 ) {
					int len = e.getValue();
					for ( int i = 0; i < len; i++ ) {
						ps.println(e.getKey() + ": 1");
					}
				} else {
					ps.println(e.getKey() + ": " + e.getValue());
				}
			}
			ps.println();
		}
	}
}
class Equipment {
	String name;
	String[] recName;
	int cost, type, sumCost, len;
	int[] recNum;
	public Equipment( String name, int cost, int type ) {
		this.name = name;
		this.cost = cost;
		this.type = type;
		if ( type != 1 ) sumCost = cost;
	}
	public void setRecipe( String[] rec ) {
		len = rec.length;
		recName = new String[ len ];
		recNum = new int[ len ];
		for ( int i = 0; i < len; i++ ) {
			String[] temp = rec[i].split(" ");
			if ( temp.length > 2 ) {
				recName[i] = temp[1];
				recNum[i] = Integer.parseInt( temp[2] );
			} else if ( temp.length == 0 ) len--;
		}
	}
}
