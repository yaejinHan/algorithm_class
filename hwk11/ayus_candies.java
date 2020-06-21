
import java.io.*;
import java.util.*;

public class ayus_candies {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        ArrayList<Long> B = new ArrayList<Long>();
        ArrayList<Long> G = new ArrayList<Long>();
        ArrayList<Long> C = new ArrayList<Long>();

        while ((line = br.readLine()) != null) {

            if (!br.ready()) break;
        }

        String[] split = line.trim().split("\\s+");

        for (int i = 0; i < split.length; i++) {
            long curr = Long.parseLong(split[i]);
            if (i == 0 || i == 3 || i == 6) B.add(curr);
            else if (i == 1 || i == 4 || i == 7) G.add(curr);
            else C.add(curr);
        }

        long minMoves = 0;
        String answer = "";

        for (int i = 0; i < 6; i++) {
            String S = "";
            long moves = 0;

            if (i == 0) {
                moves = B.get(1) + B.get(2) + C.get(0) + C.get(2) + G.get(0) + G.get(1);
                S = "BCG";
                answer = S;
                minMoves = moves;
            }

            else if (i == 1) {
                moves = B.get(1) + B.get(2) + G.get(0) + G.get(2) + C.get(0) + C.get(1);
                S = "BGC";
                if (moves < minMoves) {
                    minMoves = moves;
                    answer = S;
                }
            }

            else if (i == 2) {
                moves = C.get(1) + C.get(2) + B.get(0) + B.get(2) + G.get(0) + G.get(1);
                S = "CBG";
                if (moves < minMoves) {
                    minMoves = moves;
                    answer = S;
                }
            }

            else if (i == 3) {
                moves = C.get(1) + C.get(2) + B.get(0) + B.get(1) + G.get(0) + G.get(2);
                S = "CGB";
                if (moves < minMoves) {
                    minMoves = moves;
                    answer = S;
                }
            }

            else if (i == 4) {
                moves = G.get(1) + G.get(2) + B.get(0) + B.get(2) + C.get(0) + C.get(1);
                S = "GBC";
                if (moves < minMoves) {
                    minMoves = moves;
                    answer = S;
                }
            }

            else if (i == 5) {
                moves = G.get(1) + G.get(2) + C.get(0) + C.get(2) + B.get(0) + B.get(1);
                S = "GCB";
                if (moves < minMoves) {
                    minMoves = moves;
                    answer = S;
                }
            }
        }

        System.out.println(answer + " " + minMoves);



	}

}
