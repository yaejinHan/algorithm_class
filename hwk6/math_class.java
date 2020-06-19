/* received help from Chrissy Jeon on simplifying the solution of the problem */ 

import java.io.*;
import java.util.*;

public class math_class {

	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = "";
        String content = "";

        while ((line = br.readLine()) != null) {
            content += line + " ";

            if (!br.ready()) break;
        }

        String[] input = content.split("\\s+");
        int numInt = Integer.parseInt(input[0]);


        Comparator<String> compareStr = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {

                // if (s1+s2) is greater than (s2+s1), it will return -1
                // if the other way around, it will return 1;
                // if the sum is equal, then it will return 0
                if ((s1+s2).compareTo(s2+s1) > 0) return -1;
                else if ((s1+s2).compareTo(s2+s1) < 0) return 1;
                else return 0;
            }
        };

        ArrayList<String> arr = new ArrayList<String>(numInt);


        String result = "";

        for (int i = 1; i <= numInt; i++) {
            arr.add(input[i]);
        }


        Collections.sort(arr, compareStr);


        for (int i = 0; i < arr.size(); i++) {
            result += arr.get(i);
        }

        bw.write(result+"\n");
        bw.flush();



    }
}
