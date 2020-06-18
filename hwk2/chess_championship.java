import java.util.ArrayList;
import java.util.Scanner;


public class chess_championship {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int numPlayers = (int)Math.pow(2, sc.nextInt());
		
		int[] playerList = new int[numPlayers];
		
		int absentNum = sc.nextInt();
		
		
		
		for (int i = 0; i < playerList.length; i++) {
			playerList[i] = 1;
		}
		
		for (int i = 0; i < absentNum; i++) {
			playerList[sc.nextInt()-1] = 0;
		}
		
		
		
		
		int walkOverCnt = countWalkOver(playerList);
		

		if (walkOverCnt == 10) System.out.print(16);
		else System.out.print(walkOverCnt);
		
		
		
		
		
		
	}
	
	public static int countWalkOver(int[] playerList) {
		ArrayList<Integer> playResult = new ArrayList<Integer>();
		
		int indx = 0;

		int walkOverCnt = 0;
		
		int absent = 0;
		int present = 0;
		
		for (int i = 0; i < playerList.length; i++) {
			if (playerList[i] == 0) absent++;
			else present++;
		}
		
		if (absent == playerList.length || present == playerList.length) 
			return 0;
		
		for (int i = 0; i < playerList.length; i+=2) {
			
			// this case (1, 1)
			if (playerList[i] == playerList[i+1] && playerList[i] != 0) {
				playResult.add(1);
				indx+=2;
				// in this case, don't increment walkOverCnt for this is
				// a normal match
			}
			
			// this case (0, 1) or (1,0)
			else if (playerList[i] != playerList[i+1]) {
				indx+=2;
				playResult.add(1); // add 1 to the list because this is a walkOverCnt 
				// adding 1 to the list since the match happened (even though there was an
				// automatic winner)
				walkOverCnt++;
				
			}
			
			// this case (0,0)	
			else {
				indx+=2;
				playResult.add(0);
			
			}
			
			boolean canceled = false;
			
			if (((indx) % 4 ==  0) && indx != 0) {
				int resultIndx = indx/4;
				// in this case, also walkover match since the element values are different
				if (playResult.get(resultIndx) != playResult.get(resultIndx-1))  {
					walkOverCnt++;

				}
				
				
				if ((playResult.get(resultIndx) == playResult.get(resultIndx-1)) && playResult.get(resultIndx) == 0) 
					canceled = true;
				
				playResult.remove(resultIndx);
				playResult.remove(resultIndx-1);
				
				
				// if there was a walkover match or a normal match, add 1
				// if there was a cancelled match (0,0) add 0
				if (canceled) playResult.add(0);
				else playResult.add(1);
				
			}
			
			
			
			
			
		}
		
		if (playResult.size() > 1) {
			if (playResult.get(0) != playResult.get(1))
				walkOverCnt++;
		}
		
	
		return walkOverCnt;
		
	}

}
