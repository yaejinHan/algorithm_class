import java.io.*;
import java.util.*;

public class follow_the_path {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        boolean first = true;
        int numRow = 0;
        int numCol = 0;
        int startingLoc = 0;


        while ((line = br.readLine()) != null) {
            if (first) {
                String[] split = line.trim().split("\\s+");
                numRow = Integer.parseInt(split[0]);
                numCol = Integer.parseInt(split[1]);
                startingLoc = Integer.parseInt(split[2]) - 1;
                first = !first;
            }

            else {
                content += line.trim() + "\n";
            }

            if (!br.ready()) break;
        }


        String[][] grid = new String[numRow][numCol];
        int[][] timeRecord = new int[numRow][numCol];
        for (int[] rows: timeRecord) {
            Arrays.fill(rows, -1);
        }
        String[] input = content.split("\n");

        for (int i = 0; i < input.length; i++) {
            String[] temp = input[i].split("");
            grid[i] = temp;
        }

        int x = 0;
        int y = startingLoc;

        String curr = grid[x][y];
        int time = 1;
        boolean isCycle = false;

        while (x >= 0 && y >= 0 && x < numRow && y < numCol) {
            curr = grid[x][y];
            if (timeRecord[x][y] == -1) {
                timeRecord[x][y] = time;
                time++;
            }
            else {
                isCycle = !isCycle;
                break;
            }

            switch (curr) {
                case ("N"): x-=1; break;
                case ("S"): x+=1; break;
                case ("W"): y-=1; break;
                case ("E"): y+=1; break;
            }
        }

        if (!isCycle) System.out.println((time-1) + " step(s) to exit");
        else {
            int loopLen = (time) - timeRecord[x][y];
            int loopStarts = timeRecord[x][y];
            System.out.println((loopStarts-1) + " step(s) before a loop of " + loopLen + " step(s)");
        }


	}

}
