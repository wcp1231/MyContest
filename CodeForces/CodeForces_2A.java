/*
  WA 了好多次！一开始是没考虑周全，后来发现是题目没理解全！
  一开始用 Map 做，然后发香没注意到分数可以是负的，因此不能在过程中记录最大
  后来只存最后的结果，然后排序。先按分数排，分数一样的按到达这个分数的先后排
  这样还是 WA ！后来再看了几次题目，发现题目是说：
  如果最后有多个最高分，不是谁先到达最高分，而是谁最先大于等于最高分。。。
 */
class CodeForces_2A {
	InputReader ir;
	Scanner sc;
	PrintStream ps;
	public void doit() throws FileNotFoundException {
		ir = new InputReader( System.in );
		ps = System.out;
		int n;
		ArrayList<Person> list = new ArrayList<Person>();
		ArrayList<Person> points = new ArrayList<Person>();
		n = ir.nextInt();
		for ( int i = 0; i < n; i++ ) {
			String name = ir.next();
			int scores = ir.nextInt();
			boolean flag = true;
			for ( Person t : list ) if ( t.name.equals(name) ) {
				t.scores += scores;
				scores = t.scores;
				if ( scores != 0 ) t.id = i;
				flag = false;
				break;
			}
			if ( flag ) {
				list.add( new Person( name, scores, i ) );
			}
			points.add( new Person( name, scores, i ) );
		}
		Collections.sort( list );
		String name = "";
		int res = list.get(0).scores, max = n;
		for ( Person i : list ) if ( i.scores == res ) {
			for ( Person p : points ) if ( i.name.equals(p.name) &&  p.scores >= res && max > p.id ){
				name = p.name;
				max = p.id;
				break;
			}
		}
		 ps.println( name );
	}
}
class Person implements Comparable<Person> {
	int scores, id;
	String name;
	public Person( String name, int scores, int id ) {
		this.name = name;
		this.scores = scores;
		this.id = id;
	}
	@Override
	public int compareTo(Person o) {
		if ( o.scores == scores ) return id - o.id;
		return o.scores - scores;
	}
}
