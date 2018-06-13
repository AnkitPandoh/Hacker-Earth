package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MostFrequent {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Map<Integer,Integer> freq = new HashMap<Integer,Integer>(); 
		int max = 0;
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int num = in.nextInt();
			if(freq.containsKey(num)){
				int count = freq.get(num);
				freq.put(num, ++count);
			}else{
				freq.put(num,1);
			}
			if(freq.get(num) == max && ans > num){
				ans = num;
			}
			if(freq.get(num) > max){
				max = freq.get(num);
				ans = num;
			}
		}
		System.out.println(ans);
	}
}
