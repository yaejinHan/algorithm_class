import java.io.*;
import java.util.*;

public class task_planning {
	
    static class recurring {
        int startT;
        int endT;
        int repInterval;

        public recurring(int startT, int endT, int repInterval) {
            this.startT = startT;
            this.endT = endT;
            this.repInterval = repInterval;
        }
    }

    static class oneTime {
        int startT;
        int endT;

        public oneTime(int startT, int endT) {
            this.startT = startT;
            this.endT = endT;
        }
    }

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        boolean first = true;
        int numOneTime = 0;
        int numRecurring = 0;

        while ((line = br.readLine())!= null) {
            if (first) {
                String[] input = line.trim().split("\\s+");
                numOneTime = Integer.parseInt(input[0]);
                numRecurring = Integer.parseInt(input[1]);
                first = !first;
                if (numOneTime == 0 && numRecurring == 0) {
                    System.out.println("NO CONFLICT");
                    return;
                }

            }

            else {
                content += line + "\n";
            }

            if (!br.ready()) break;
        }


        BitSet bs = new BitSet(1000001);
        
        ArrayList<recurring> recurringLst = new ArrayList<>();
        ArrayList<oneTime> oneTimeLst = new ArrayList<>();

        String[] input = content.split("\n");
        int cnt = 0;

        while (cnt < numOneTime) {
            String[] curr = input[cnt].trim().split("\\s+");

            int startT = Integer.parseInt(curr[0]);
            int endT = Integer.parseInt(curr[1]);

            oneTimeLst.add(new oneTime(startT, endT));


            cnt++;

        }


        while (cnt < input.length) {
            String[] curr = input[cnt].trim().split("\\s+");
            int startT = Integer.parseInt(curr[0]);
            int endT = Integer.parseInt(curr[1]);
            int repInterval = Integer.parseInt(curr[2]);

            recurringLst.add(new recurring(startT, endT, repInterval));
            cnt++;

        }


        for (int i = 0; i < oneTimeLst.size(); i++) {

            oneTime curr = oneTimeLst.get(i);
            for (int j = curr.startT; j < curr.endT; j++) {
                if (bs.get(j)) {
                    System.out.println("CONFLICT");
                    return;
                }
                bs.set(j);
            }
        }

        for (int i = 0; i < recurringLst.size(); i++) {
            recurring curr = recurringLst.get(i);
            while (curr.endT <= 1000000) {
                for (int j = curr.startT; j < curr.endT; j++) {
                    if (bs.get(j)) {
                        System.out.println("CONFLICT");
                        return;
                    }
                    bs.set(j);
                }

                curr.startT += curr.repInterval;
                curr.endT += curr.repInterval;
            }
        }


        System.out.println("NO CONFLICT");


	}

}
