import java.util.*;

public class hwk1_fib {

	public static void main(String[] args) {

		

		Scanner sc = new Scanner(System.in);

		System.out.println(fib(sc.nextInt()));

	}
	
	
	
	

	public static int fib(int N) {
		// length set to N+1 in case N == 1
		// even in case of N == 1, we still want to initialize
		// our base cases of fib[0] and fib[1] 
		 
		int fib[] = new int[N+1];
		

		fib[0] = 1;
		fib[1] = 1;
			
		
		
		
		int indx = N - 1;
		
		if (indx >= 2) {
			for (int i = 2; i <= indx; i++) {
				fib[i] = fib[i-1] + fib[i-2];
			}
		}
		
		return fib[indx];
	}

}
