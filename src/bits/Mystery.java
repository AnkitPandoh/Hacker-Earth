package bits;

import java.util.Scanner;

public class Mystery {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(s.hasNext()){
			long n = s.nextLong();
			long count = 0;
			while(n != 0){
				count++;
				n = n &(n-1);
			}
			System.out.println(count);
		}
	}
}
