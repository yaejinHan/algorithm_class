import java.util.*;
import java.io.*;

public class array_partition {
	
	
	/*
	 * Referenced one of the youtube videos that talks about the implementation of this problem
	 * the video can be found here -  https://www.youtube.com/watch?v=9BnC7orwkNA
	 */
	
    static long minP(long[] arr, long currMax) {

        long totalPartitionNeeded = 1;
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > currMax) {
                sum = arr[i];
                totalPartitionNeeded++;
            }
        }

        return totalPartitionNeeded;
    }


	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        boolean first = true;
        long cnt = 0;
        long N = 0;
        long M = 0;


        while ((line = br.readLine()) != null) {
            if (first) {
                String[] split = line.split("\\s+");
                N = Long.parseLong(split[0]);
                M = Long.parseLong(split[1]);
                first = !first;
            }

            cnt++;
            if (cnt == 2) break;

        }


        String[] split = line.split("\\s+");
        long[] arr = new long[split.length];
        long total = 0;
        long max = 0;

        for (int i = 0; i < split.length; i++) {
            arr[i] = Long.parseLong(split[i]);
            if (arr[i] > max) max = arr[i];
            total += arr[i];
        }


        long start = max;
        long end = total;
        long mid = 0;
        long minPartitionNeeded = 0;

        long minimizedMax = 0;

        while (start < end) {
            mid = (start+end)/2;
            minPartitionNeeded = minP(arr, mid);
            if (minPartitionNeeded <= M) {
                end = mid;
                minimizedMax = mid;
            }
            else {
                start = mid+1;
            }

        }


        System.out.println(start);


	}

}
