import java.io.*;
import java.util.*;

public class xiangliu {
	
	/* this method is similar to the method we did in class during Sorting and Searching    */
    static int findArcher(long target, int[] archer, int numArcher, int indx) {
        int start = indx;
        int end = numArcher - 1;
        int mid = 0;


        while (start <= end) {
            mid = (start + end) / 2;

            if (target == archer[mid]) {
                if (target > archer[mid - 1]) return mid;
                else end = mid - 1;
            } else if (target < archer[mid]) {
                if (target > archer[mid - 1]) return mid;
                else end = mid - 1;
            } else start = mid + 1;


        }

        return -1;
    }


	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        boolean first = true;
        int numHead = 0;
        int numArcher = 0;
        PriorityQueue<Integer> archer = new PriorityQueue<>();
        PriorityQueue<Integer> head = new PriorityQueue<>();
        int maxHeadSize = 0;
        int maxArcherStrength = 0;



        int headCnt = 0;
        int archerCnt = 0;

        while ((line = br.readLine()) != null) {
            if (first) {
                String[] split = line.split("\\s+");
                numHead = Integer.parseInt(split[0]);
                numArcher = Integer.parseInt(split[1]);
                first = !first;
            }

            else if (headCnt < numHead){
                int headSize = Integer.parseInt(line);
                head.add(headSize);
                if (headSize > maxHeadSize) maxHeadSize = headSize;
                headCnt++;
            }

            else {
                int archerStrength = Integer.parseInt(line);
                archer.add(archerStrength);
                if (archerStrength > maxArcherStrength) maxArcherStrength = archerStrength;
                archerCnt++;
            }

            if (!br.ready()) break;
        }

        long sum = 0;


        if (head.size() > archer.size() || maxHeadSize > maxArcherStrength) {
            System.out.println("Xia is doomed!");
            return;
        }

        while ((!head.isEmpty())) {
            int headSize = head.poll();
            while (archer.peek() < headSize) {
                archer.poll();
            }

            int rightArcher = archer.poll();
            sum += rightArcher;

            if (archer.isEmpty() && !head.isEmpty()) {
                System.out.println("Xia is doomed!");
                return;
            }

        }

        System.out.println(sum);




	}

}
