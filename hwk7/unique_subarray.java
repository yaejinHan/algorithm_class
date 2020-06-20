
import java.util.*;
import java.io.*;

public class unique_subarray {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        int num = 0;
        boolean first = true;

        while ((line = br.readLine()) != null) {

            if (first) {
                first = !first;

            }
            else {
                content += line + " ";
            }

            if (!br.ready()) break;
        }


        String[] input = content.split("\\s+");
        int len = 0;
        int maxLen = 0;
        int indx = 0;
        Hashtable<String, Integer> h = new Hashtable<String, Integer>();

        while (indx < input.length) {
            String curr = input[indx];
            if (h.containsKey(curr) && h.get(curr) != indx) {
                int temp = h.get(curr);
                h.replace(curr, temp, indx);
                if (len > maxLen) maxLen = len;
                len = 0;
                indx = temp+1;
                h.clear();
            }

            else if (!h.containsKey(curr)) {
                h.put(curr, indx);
                indx++;
                len++;
            }

            else {
                indx++;
                len++;
            }


        }

        if (len > maxLen) maxLen = len;
        System.out.println(maxLen);

	}

}
