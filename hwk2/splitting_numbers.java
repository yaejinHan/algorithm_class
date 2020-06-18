import java.util.Scanner;


public class splitting_numbers {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		int input = sc.nextInt();
		
		String n = reversedBinaryConversion(input);
		
		
		String split[] = n.split("");
		
		boolean aTurn = true;
		
		int[] a = new int[split.length];
		int[] b = new int[split.length];
		
		
		for (int i = 0; i < split.length; i++) {
			if (Integer.parseInt(split[i])== 1) {
				if (aTurn) {
					a[i] = 1;
					aTurn = false;
				}
				else {
					b[i] = 1;
					aTurn = true;
				}
			}
		}
		
	
		
		
		int finalA = decimalConversion(a);
		int finalB = decimalConversion(b);
		
		System.out.println(finalA + " " + finalB);
	

	}
	
	public static String reversedBinaryConversion(int input) {
		String binaryN = "";
		
		while (input != 0) {
			binaryN += Integer.toString(input % 2);	
			input = input / 2;
		}
		return binaryN;
	}
	
	
	public static int decimalConversion(int[] arr) {
		double output = 0;
		
		for (int i = arr.length-1; i >= 0; i--) {
			output += (arr[i] * Math.pow(2, i));
		}
		
		return (int)output;
	}

}
