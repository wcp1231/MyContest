/*
ID: wcp12311
LANG: JAVA
TASK: contact
*/
// 原来BufferedReader比Scanner快好多。。。
// Hashtable 也比 HashMap 要快好多。。。
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;

public class USACO3_1_contact {
    
	public static void main( String[] args ) throws IOException {
    	new USACO3_1_contact().doit();
    }
}
class USACO3_1_contact {
	PrintStream ps;
	public void doit() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		ps = new PrintStream(new FileOutputStream("contact.out"));
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		StringBuffer sb = new StringBuffer();
		String readline = f.readLine();
       while (readline != null)
        {
            sb.append(readline);
            readline = f.readLine();
        }
		String text = sb.toString();
		System.out.println(text);
		int len = text.length();
		Hashtable<String, MyPattern> table = new Hashtable<String, MyPattern>();
		ArrayList<MyPattern> list = new ArrayList<MyPattern>();
		for ( int l = a; l <= b; l++ ) {
			for ( int idx = 0; idx+l <= len; idx++ ) {
				String substr = text.substring( idx, idx+l );
				MyPattern p = table.get( substr );
				if ( p == null ) {
					p = new MyPattern( substr );
					table.put( substr, p );
					list.add( p );
				}
				p.count ++;
			}
		}
		Collections.sort( list );
		int cnt = 0, p = 0, per = -1, num = 0;
		int size = list.size();
		while ( cnt <= n ) {
			MyPattern temp = list.get( p );
			if ( temp.count != per ) {
				if ( per != -1 ) ps.println();
				if ( cnt >= n ) break;
				per = temp.count;
				ps.println( per );
				ps.print( temp.sequence );
				num = 1;
				p++;
				cnt++;
			} else {
				if ( num > 0 ) ps.print(" ");
				else if ( num == 0 ) ps.println();
				ps.print(temp.sequence );
				num++;
				p++;
				if ( num == 6 ) {
					num = 0;
				}
			}
			if ( p >= size ) {
				ps.println();
				break;
			}
		}
		ps.close();
		System.exit( 0 );
	}
}
class MyPattern implements Comparable<MyPattern> {
	String sequence;
	int count;
	public MyPattern( String s ) {
		sequence = s;
		count = 0;
	}
	@Override
	public int compareTo(MyPattern o) {
		if ( count == o.count ) {
			if ( sequence.length() == o.sequence.length() ) return Integer.parseInt( sequence, 2 ) - Integer.parseInt( o.sequence, 2 );
			return sequence.length() - o.sequence.length();
		}
		return o.count - count;
	}
	
}
