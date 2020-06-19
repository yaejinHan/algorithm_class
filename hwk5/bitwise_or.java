/// used the algorithm discussed in class


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bitwise_or {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";

        while ((line = br.readLine()) != null) {
            if (!br.ready()) break;
        }

        String[] input = line.split(" ");

        long n = Long.parseLong(input[0]);
        long l = Long.parseLong(input[1]);
        long r = Long.parseLong(input[2]);
        boolean inRange = false;

        long x = 0;

        if (l == r) {
            System.out.println(l);
            return;
        }

        for (int i = 31; i >= 0; i--) {
            long mask = 1<<i;
            int currNBit = (int)(n&mask);

            if (currNBit != 0) currNBit = 1;
            else currNBit = 0;

            long currXVal = (long)Math.pow(2, i);
            if (!inRange) {
                if (x + (currXVal-1) < l) {
                    x += currXVal;
                }
                else {
                    if (currNBit == 0 && (x + (currXVal)) <= r) {
                        x += currXVal;
                    }
                }
                if (x  >= l) {
                    inRange = !inRange;
                }
            }

            else {
                if (currNBit == 0 && (x + (currXVal)) <= r) {
                    x += currXVal;
                }
            }

            mask >>=1;
        }

        System.out.println(x);
    }
}
