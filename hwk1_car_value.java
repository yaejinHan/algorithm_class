import java.util.Scanner;


public class hwk1_car_value {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		double loan[] = new double[4];
		
		for (int i = 0; i < loan.length; i++) {
			loan[i] = sc.nextDouble();
		}
		
		
		// number of rows of depreciation matrix is the number of depreciation records we get from the input
		double depreciation[][] = new double[(int)loan[3]][2];
		
		for (int i = 0; i < loan[3]; i++) {
			for (int j = 0; j < 2; j++) {
				depreciation[i][j] = sc.nextDouble();
			}
		}
		
		// matrix holding depreciation records to be used during calculation
		double depreciationCalc[][] =  new double[(int)loan[0]][2];
 		
		for (int i = 0; i < depreciationCalc.length; i++) {
			for (int j = 0; j < depreciationCalc[0].length; j++) {
				if (i > (depreciation.length-1)) {
					depreciationCalc[i][j] = 0;
			
				}
				else {
					depreciationCalc[i][j] = depreciation[i][j];
				}
			}
		}
	
		// initialize car value array and amount owed array with length the duration months of the loan
		double carVal[] = new double[(int)loan[0]];
		double amtOwed[] = new double[(int)loan[0]];
		
		
		// initial car value is the down payment + loan amount

		double monthlyPay = loan[2]/loan[0];					
		
		double currDepreciationRates = 0;
		double prevDepreciationRates = 0;
		

		// index count for depreciationCalc
		int indxCnt = 0;
		
		// loop until we find the output month that we want
		// until we reach that duration months of the loan
		for (int i = 0; i < (int)loan[0]; i++) {
			if (i == 0) {
				// initial values of car value and amount owed are given from the input * depreciation 
				carVal[0] = (loan[1] + loan[2]) * (1 - depreciationCalc[indxCnt][1]);
				
				amtOwed[0] = loan[2];
				
				currDepreciationRates = depreciationCalc[indxCnt][1];
				prevDepreciationRates = currDepreciationRates;
				//only increment index because there was a match case of i == deprecationCalc[indxCnt][1]
				indxCnt++;
			}
			
			else {
				
				if (i == (int)depreciationCalc[indxCnt][0]) {
					currDepreciationRates = depreciationCalc[indxCnt][1];
					prevDepreciationRates = currDepreciationRates;
					
					//only increment index because there was a match case of i == deprecationCalc[indxCnt][1]
					indxCnt++;


				}
							
				else {
					currDepreciationRates = prevDepreciationRates;
					// we're not incrementing indxCnt since there wasn't a match case
					// between i and deprecationCalc[indxCnt][1]
				}
				
				
				carVal[i] = carVal[i-1] * (1 - currDepreciationRates);
				amtOwed[i] = amtOwed[i-1] - monthlyPay;

				
			}
			

	
			if (carVal[i] > amtOwed[i]) {
				if (i == 1) System.out.println(i + " month");
				else System.out.println(i + " months");
				return;
			}
			
		}
		
		// if nothing returned up to this point,
		// it means car value is greater than the amount owed at the last month of the loan duration
		System.out.println((int)loan[0] + " months");
		
		

	}

}
