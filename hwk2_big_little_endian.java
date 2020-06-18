import java.util.Scanner;


public class hwk2_big_little_endian {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		int input = sc.nextInt();
		
		System.out.printf("%d converts to %d", input, Integer.reverseBytes(input));
	
	}

}
