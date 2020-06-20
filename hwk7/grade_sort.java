import java.util.*;
import java.io.*;

public class grade_sort {
	   //// used methods countElements and sort written in baeldung website as a helper function for this problem
    /// the code can be found here - https://www.baeldung.com/java-counting-sort

    static int[] countElements(int[] input, int k) {
        int[] c = new int[k + 1];

        for (Integer i : input) {
            c[i] += 1;
        }

        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        return c;
    }

    static int[] sort(int[] input, int k) {
        int[] c = countElements(input, k);

        int[] sorted = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            int current = input[i];
            sorted[c[(current)] - 1] = (current);
            c[(current)] -= 1;
        }

        return sorted;
    }


	public static void main(String[] args) throws IOException {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        String line = "";
	        String content = "";
	        int num = 0;
	        boolean first = true;

	        while ((line = br.readLine()) != null) {
	            if (first) {
	                String[] split = line.split(" ");
	                num = Integer.parseInt(split[0]);
	                first = !first;

	            }
	            else {
	                content += line;
	            }

	            if (!br.ready()) break;
	        }


	        int[] arr = new int[num];
	        String[] input = content.split("\\s+");

	        for (int i = 0; i < arr.length; i++) {
	            arr[i] = Integer.parseInt(input[i]);
	        }


	        int[] sorted = sort(arr, 100);
	        for (int i = 0; i < sorted.length-1; i++) {
	            bw.write(sorted[i] + " ");
	        }
	        bw.write(sorted[sorted.length-1] + "\n");
	        bw.flush();


    }


}
