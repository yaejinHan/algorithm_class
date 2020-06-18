//// collaborated with Gayeon Park

import java.util.Scanner;

public class hwk1_rod_sculpter {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		String currVal = "+x";
		String posX = "+x";
		String negX = "-x";
		String posY = "+y";
		String negY = "-y";
		String posZ = "+z";
		String negZ = "-z";
		String no = "No";
		
		int rodLen = sc.nextInt();
		
		for (int i = 0; i < rodLen - 1; i++) {
			String instruction = sc.next();
			
			
			if (!instruction.equals(no)) { 
				if (currVal.equals(posX)) {
					currVal = instruction;
				}
				
				
				// when currVal not equal to posX
				else {
					// when two same coordinate with the same sign
					// output = negX
					if (currVal.equals(instruction)) {
						currVal = negX;
					}
					
					
					// when two same coordinate but different sign
					// output = posX
					else if (currVal.charAt(1)==(instruction.charAt(1))) {
						currVal = posX;
					}
					
					// in case of currVal = negX
					// output the reverse sign of the instruction
					else if (currVal.equals(negX)) {
						if (instruction.equals(posY)) currVal = negY;
						
						else if (instruction.equals(negY)) currVal = posY;
						
						else if (instruction.equals(posZ)) currVal = negZ;
						
						else currVal = posZ;
					}
						
				}
				
			}
			

		}
		System.out.print(currVal);


	
		

	}

}

