import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class preparing_problems {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";

        while ((line = br.readLine())!=null) {
            content += line + " ";

            if (!br.ready()) break;
        }

        String[] input = content.split(" ");

        int s = Integer.parseInt(input[0]);
        String[] created = Arrays.copyOfRange(input, 1, 13);
        String[] needed = Arrays.copyOfRange(input, 13, 25);
        int[] ans = new int[12];


        for (int i = 0; i < needed.length; i++) {
            if (s >= Integer.parseInt(needed[i])) {
                ans[i] = 1;
                s = (s-Integer.parseInt(needed[i])) + Integer.parseInt(created[i]);
            }
            else {
                s = s + Integer.parseInt(created[i]);
            }
        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) System.out.println("No homework.");

            else System.out.println("A lot of grading.");
        }


	}

}
