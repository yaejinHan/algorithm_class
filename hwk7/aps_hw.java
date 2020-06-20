import java.io.*;
import java.util.*;

public class aps_hw {
	
    static class Problem {
        int A = 0;
        int B = 0;

        public Problem(int A, int B) {
            this.A = A;
            this.B = B;
        }

    }


	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        boolean first = true;
        PriorityQueue<Problem> pq = new PriorityQueue<Problem>(new Comparator<Problem>() {
            @Override
            public int compare(Problem p1, Problem p2) {
                if (p1.B > p2.B) return -1;
                else if (p1.B < p2.B) return 1;
                return 0;
            }
        });

        while ((line = br.readLine())!= null) {
            if (first) {
                first = !first;
            }

            else {
                String[] split = line.split("\\s+");
                pq.add(new Problem(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }
            if (!br.ready()) break;
        }

        int prevProbT = 0;
        long time = 0;

        while (!pq.isEmpty()) {
            Problem currP = pq.poll();

            int currT = prevProbT + currP.A + currP.B;
            if (time < currT) time = currT;
            prevProbT += currP.A;
        }

        System.out.println(time);


	}

}
