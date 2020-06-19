//// received help from Mark Chen and Chrissy Jeon


import java.io.*;
import java.util.*;


public class cims_printer {

	static class myJob {
		int priority = 0;
		boolean myJob = false;
		
		myJob(int val, boolean myJob) {
			this.priority = val;
			this.myJob = myJob;
		}
		
	}

	public static void main(String[] args) throws IOException {
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringBuffer sb = new StringBuffer();

	        String line = "";
	        String content = "";

	        while ((line = br.readLine()) != null) {
	            content += line + " ";

	            if (!br.ready()) break;
	        }

	        String[] input = content.split(" ");

	        int myJobP = 0;
	        int myJobIndx = 0;
	        int timeNeeded = 0;
	        int initialQSize = 0;

	        initialQSize = Integer.parseInt(input[0]);
	        myJobIndx = Integer.parseInt(input[1]);

	        Queue<myJob> Q = new LinkedList<myJob>();
	        Queue<Integer> Q2 = new LinkedList<Integer>();

	        if (initialQSize == 1) {
	            System.out.print(1);
	            return;
	        }


	        for (int i = 2; i < input.length; i++) {
	            if ((i) ==  myJobIndx+2) {
	                myJob mj = new myJob(Integer.parseInt(input[i]), true);
	                Q.add(mj);

	            }
	            else {
	                Q.add(new myJob(Integer.parseInt(input[i]), false));
	            }
	            Q2.add(Integer.parseInt(input[i]));


	        }

	        boolean smallerVal = false;

	        while (!Q.isEmpty()) {
	            int max = Collections.max(Q2);

	            if (max > Q.peek().priority) {
	                smallerVal = true;

	            }

	            if (smallerVal) {
	                myJob polled = Q.poll();
	                Q.add(polled);
	            }

	            else {
	                if (Q.peek().myJob) {
	                    timeNeeded++;
	                    System.out.println(timeNeeded);
	                    return;
	                }
	                else {
	                    timeNeeded++;
	                    int polled = Q.poll().priority;
	                
	                    Q2.remove(max);
	   
	                }
	            }
	            smallerVal = false;
	        }


	        System.out.println(timeNeeded);
	    }
	}
