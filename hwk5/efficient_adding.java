import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class efficient_adding {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        while ((line = br.readLine()) != null) {
            content += line + " ";

            if (!br.ready()) break;
        }

        br.close();

        PriorityQueue<Long> q = new PriorityQueue<Long>();
        String[] input = content.split(" ");
        long sum = 0;
        long cost = 0;
        long indx = 0;

        long n = Long.parseLong(input[0]);
        long[] sumTable = new long[(int) n];

        for (int i = 1; i <= n; i++) {
            q.add(Long.parseLong(input[i]));
        }

        while (q.size() >=2) {
            sum = q.poll() + q.poll();
            q.add(sum);
            sumTable[(int) indx] = sum;
            indx++;
        }


        for (int i = 0; i < indx-1; i++) {
            cost += sumTable[i];
        }
        cost += sum;
        System.out.println(cost);
	}

}
