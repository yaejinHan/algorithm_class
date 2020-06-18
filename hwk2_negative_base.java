import java.util.Scanner;


public class hwk2_negative_base {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int input = sc.nextInt();
		
		
		// base -2 conversion
		System.out.print(baseConversion(input));

	}
	
	
	public static String baseConversion(int input) {
		String output = "";
		
		// one of the edge case of input = 0
		if (input == 0) return "0";
		
	
		boolean changed = false;
		
		// while input is not 0, keep dividing the input by -2
		while (input != 0) {
			

			// for all input value (or quotient) that is negative and odd
			// do the calculation below
			if (input < 0 && input % 2 != 0)  {
				input = Math.abs(input) + 2;
				changed = true;
			}
						
			int remainder = input % (-2);
		
			output  = Integer.toString(remainder) + output;
	
			input /= -2;
			
			if (changed) {
				input = Math.abs(input);
				// put the state back to false when computation done
				changed = false;
			}
			
			
		}

		return output;
	}
	

	
	


}
