import java.io.*;
import java.util.*;

public class jacobs_ladder {

	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        String num = "";
        boolean first = true;

        while ((line = br.readLine())!=null) {
            if (first) {
                num += line;
                first = !first;
            }

            else content += line + " ";


            if (!br.ready()) break;
        }



        String[] input = content.split(" ");

        if (input.length == 1) {
            System.out.println(input[0]);
            return;
        }

        int max = -1;
        int secondMax = -1;
        int difference = 0;
        long ans = 0;
        boolean shouldIncrement = false;

        int[] diff = new int[input.length];


        for (int i = 0; i < input.length-1; i++) {
            if (i == 0) {
                difference = Integer.parseInt(input[i]);
                diff[i] = difference;
                difference = Integer.parseInt(input[i+1])-Integer.parseInt(input[i]);
            }
            else difference = Integer.parseInt(input[i+1])-Integer.parseInt(input[i]);

            diff[i+1] = difference;
        }

        for (int i = 0; i < diff.length; i++) {
            int curr = diff[i];
            if (curr > max) {
                max = curr;
                secondMax = max - 1;
                shouldIncrement = false;
            } else if (curr == secondMax) secondMax--;

            else if (curr > secondMax) shouldIncrement = true;

        }


        if (shouldIncrement) ans = max+1;
        else ans = max;

        System.out.println(ans);



	}

}
