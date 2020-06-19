//// referenced what we did in class

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class running_median {

	public static void main(String[] args) throws IOException {

	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	        StringBuffer sb = new StringBuffer();
	        ArrayList<Long> arr = new ArrayList<Long>();
	        String line = "";


	        while ((line = br.readLine()) != null) {

	            if (line.trim().length() == 0) continue;
	            String val = line.trim();
	            arr.add(Long.parseLong(val));

	            if (!br.ready()) break;
	        }

	        br.close();

	        PriorityQueue<Long> min = new PriorityQueue<>();
	        PriorityQueue<Long> max = new PriorityQueue<>(Collections.reverseOrder());

	        long runningM = 0;

	        for (int i = 0; i < arr.size(); i++) {
	            // first time
	            if (i == 0) {
	                long val = arr.get(i);
	                max.add(val);
	                runningM = val;
	            }

	            else {
	                long curr = arr.get(i);
	                if (curr > runningM)  min.add(curr);

	                else  max.add(curr);

	                if (min.size() == max.size()) {
	                    runningM = (min.peek()+max.peek())/2;
	                }

	                else if (Math.abs(min.size()-max.size()) > 1) {

	                    if (min.size() > max.size()) {
	                        long move = min.poll();
	                        max.add(move);
	                    }
	                    else {
	                        long move = max.poll();
	                        min.add(move);
	                    }

	                    runningM = (min.peek()+max.peek())/2;
	                }

	                else {
	                    if (min.size() > max.size()) {
	                        runningM = min.peek();
	                    }
	                    else {
	                        runningM = max.peek();
	                    }

	                }
	            }

	            sb.append(runningM + "\n");

	        }

	        bw.write(String.valueOf(sb));
	        bw.flush();


	    
    }
}
