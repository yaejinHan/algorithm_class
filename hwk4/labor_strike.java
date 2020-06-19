import java.util.*;
import java.io.*;

public class labor_strike {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		String line = "";
		
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
			
			if (!br.ready()) break;
		}
		
		line = br.readLine();
		sb.append(line);
		
		Scanner sc = new Scanner(sb.toString());
		
		int numDays = sc.nextInt();
		int[] days = new int[numDays+1];
		int numLabr = sc.nextInt();
		int noSubway = 0;
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for (int i = 0; i < numLabr; i++) {
			al.add(sc.nextInt());
		}
		
	
		while (!al.isEmpty()) {
			int s_i = al.get(0);
			for (int i = s_i; i < days.length; i+= s_i) {
				if (days[i] != -1) {
					days[i] = -1;
					if (i != 6 && i != 7 && i % 7 != 6 && i % 7 != 0) {
						noSubway++;
					}
				}
			}
			
			al.remove(0);
			
		}

		
		System.out.println(noSubway);
	}

}
