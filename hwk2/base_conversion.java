import java.util.Scanner;


public class base_conversion {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		int origBase = sc.nextInt();
		
		int finalBase = sc.nextInt();
		
		String input = sc.next();
		
		
		long decimalVal = decimalConversion(input, origBase);
	
		if (decimalVal != -1) {
			String outputVal = baseConversion(decimalVal, finalBase);
	
			System.out.print(input + " base " + origBase + " = " + outputVal + " base " + finalBase);

		}
		
		else System.out.print(input + " is an illegal base " + origBase + " number");

	
		

	}
	
	
	
	
	
	public static long decimalConversion(String input, int origBase) {
		String inputReversed = reverse(input);
		long decimalVal = 0;
		for (int i = 0; i < input.length(); i++) {
			int numericVal = Character.getNumericValue(inputReversed.charAt(i));
			
			if (input.equals("0")) return 0;
			
			if (numericVal >= origBase) return -1;
			
			decimalVal += numericVal * Math.pow(origBase, i);
		}
		
		return decimalVal;
	}

	
	
	
	public static String baseConversion(long decimalVal, int finalBase) {
		
		int digitBase = 10;
		String[] letterArr = {"A", "B", "C", "D", "E", "F"};
		
		String finalVal = "";
		
		if (decimalVal == 0) return "0";
		while (decimalVal != 0) {
			int remainder = (int)(decimalVal % finalBase);
			
			if (remainder >= 10) 
				finalVal += letterArr[(int) (remainder - digitBase)];
			else finalVal += Long.toString(remainder);
			
			decimalVal /= finalBase;
		}
		
		
		return reverse(finalVal);
		
		
	}
	
	public static String reverse(String variable) {
		String reversed = "";
		
		for (int i = variable.length()-1; i >= 0; i--) {
			reversed += variable.charAt(i);
		}
		
		return reversed;
	}
	
}
