/// Chrissy (Juhee) Jeon helped me understand the concept of this program
// and suggested to me to use array instead of arraylist

import java.util.Scanner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class awesome_number {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		int N = sc.nextInt();
	
		boolean[] pLst = new boolean[N+1];
		Arrays.fill(pLst, true);
		
		if (N < 11) {
			System.out.println(0);
			return;
		}
		
		pLst[0] = false;
		pLst[1] = false;

		int cnt = 0;
		for (int i = 2; i <= N; i++) {
			
			if (pLst[i] == true) {
				
				
				if (i % 10 == 1) {
					cnt++;
				}
				for (int j=i+i; j <= N; j += i) {
						pLst[j] = false;
				}
			}
			
		}
	
		System.out.println(cnt);
		
	
	}
}
