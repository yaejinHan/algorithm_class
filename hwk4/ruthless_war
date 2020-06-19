
import java.io.*;
import java.util.*;


public class ruthless_war {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        
        StringBuffer sb = new StringBuffer();

        while ((line = br.readLine())!= null) {
            sb.append(line + " ");

            if (!br.ready()) break;
        }

        line = br.readLine();
        sb.append(line);


        br.close();


        Scanner sc = new Scanner(sb.toString());

        int numSoldiers = sc.nextInt();
        int numReports = sc.nextInt();

        BitSet soldiers = new BitSet(numSoldiers);
        soldiers.flip(0, numSoldiers);

        String leftPal ="";
        String rightPal = "";


        if (numSoldiers == numReports) {
            for (int i = 0; i < numReports; i++) {
                System.out.println("* *");

            }
            return;
        }

        for (int i = 0; i < numReports; i ++) {
            boolean leftPalSet = false;
            boolean rightPalSet = false;

            int start = sc.nextInt()-1;
            int end = sc.nextInt()-1;
            soldiers.clear(start, end+1);

            if (start == 0) {

                leftPalSet = true;
                leftPal = "*";
            }

            if (end == (numSoldiers-1)) {
                rightPalSet = true;
                rightPal = "*";
            }

            if (rightPalSet && leftPalSet) {
                System.out.println(leftPal + " " + rightPal);
                return;
            }

            if (!leftPalSet) {
                if (Integer.parseInt(String.valueOf(soldiers.previousSetBit(start))) == -1)
                    leftPal = "*";

                else
                    leftPal = Integer.toString(soldiers.previousSetBit(start)+1);
            }


            if (!rightPalSet) {
                if (Integer.parseInt(String.valueOf(soldiers.nextSetBit(end))) == -1)
                    rightPal = "*";
                else
                    rightPal = Integer.toString(soldiers.nextSetBit(end)+1);
            }

            System.out.println(leftPal + " " + rightPal);
        
        }


    }

}
