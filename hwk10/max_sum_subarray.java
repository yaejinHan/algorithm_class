import java.io.*;
import java.util.*;

public class max_sum_subarray {
	
	
    static int findValidElement(String[] arr, int indx) {
        while (Long.parseLong(arr[indx]) < 0 && indx < arr.length) {
            indx++;

            if (indx == arr.length -1 && Long.parseLong(arr[indx]) < 0) return -1;
        }

        return indx;
    }



	public static void main(String[] args) throws IOException  {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        String line = "";
	        int numElements = 0;
	        boolean first = true;

	        while ((line = br.readLine()) != null) {
	            if (line.equals("")) continue;
	            if (first) {
	                numElements = Integer.parseInt(line.trim());
	                first = !first;
	            }

	            if (!br.ready()) break;
	        }

	        String[] split = line.split("\\s+");

	        long max = Integer.MIN_VALUE;
	        int maxElement = Integer.MIN_VALUE;
	        long sum = 0;

	        for (int i = 0; i < split.length; i++) {
	            int curr = Integer.parseInt(split[i]);
	            sum += curr;
	            if (sum < 0) {
	                sum = 0;
	            }
	            else {
	                if (sum  > max) max = sum;
	            }

	            if (curr > maxElement) maxElement = curr;
	        }

	        if (max == Integer.MIN_VALUE) max = maxElement;


	        bw.write(max + "\n");
	        bw.flush();




	}

}
