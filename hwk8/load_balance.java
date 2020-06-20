import java.util.*;
import java.io.*;

public class load_balance {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int numNodes = 0;
        int numTasks = 0;
        boolean first = true;

        while ((line = br.readLine()) != null) {
            if (first) {
                String[] firstLine = line.split("\\s+");
                numNodes = Integer.parseInt(firstLine[0]);
                numTasks = Integer.parseInt(firstLine[1]);
                first = !first;
            }
            else {

            }
            if (!br.ready()) break;
        }


        int totalPowerNeeded = 0;
        double avgLoad = 0;
        String[] tasks = line.split("\\s+");
        int[] nodes = new int[numNodes];
        PriorityQueue<Integer> Q = new PriorityQueue<Integer>(numTasks,Collections.reverseOrder());
        for (String s: tasks) {
            int curr = Integer.parseInt(s);
            Q.add(curr);
            totalPowerNeeded += curr;
        }

        avgLoad = (double)totalPowerNeeded/numNodes;
        int indx = 0;
        int currCnt = 0;

        while (!Q.isEmpty()) {
            indx = 0;
            currCnt = 0;

            while (currCnt != numNodes && !Q.isEmpty()) {
                nodes[indx] += Q.poll();
                indx++;
                currCnt++;
            }

            indx = nodes.length - 1;
            currCnt = 0;
            while (currCnt != numNodes && !Q.isEmpty()) {
                nodes[indx] += Q.poll();
                indx--;
                currCnt++;
            }

        }

        double imbalance = 0;
        for (int i = 0; i < nodes.length; i++) {
            imbalance += (Math.abs(nodes[i] - avgLoad));
        }

        System.out.printf("IMBALANCE = %.5f", imbalance);
        System.out.println();

		

	}

}
