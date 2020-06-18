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
	
		//ArrayList<Boolean> pLst = new ArrayList<Boolean>(Arrays.asList(new Boolean[N+1]));
		//Collections.fill(pLst, Boolean.TRUE);
		
		boolean[] pLst = new boolean[N+1];
		Arrays.fill(pLst, true);
		
		if (N < 11) {
			System.out.println(0);
			return;
		}
		
		pLst[0] = false;
		pLst[1] = false;
		//pLst.set(0, false);
	//	pLst.set(1, false);
		

		int cnt = 0;
		for (int i = 2; i <= N; i++) {
			
			if (pLst[i] == true) {
			//if (pLst.get(i) == true) {
				
				
				if (i % 10 == 1) {
			//		System.out.println(i);
					cnt++;
				}
				for (int j=i+i; j <= N; j += i) {
				//	if (j % i == 0) {
						pLst[j] = false;
						//pLst.set(j, false);
					//} 
				}
			}
			
		}
	
		System.out.println(cnt);
		
	
	}
}
