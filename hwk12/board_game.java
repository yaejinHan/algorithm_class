import java.io.*;
import java.util.*;

public class board_game {
	
    static boolean safeCell(int[][] copy, int row, int col) {
        for (int i = 0; i < col; i++) {
            if (copy[row][i] == 1001) return false;
        }

        return true;
    }

    static class Soln {
        ArrayList<ArrayList<Integer>> soln = new ArrayList<ArrayList<Integer>>();
    }

    /* referenced this code from GeeksForGeeks
       the code can be found here - https://www.geeksforgeeks.org/printing-solutions-n-queen-problem/?ref=rp   */
    static void game(int[][] board, int[][] copy, int col, Soln s, ArrayList<Integer> temp) {
        if (col == board.length) return;

        for (int i = 0; i < board.length; i++) {
            if (safeCell(copy, i, col)) {
                temp.add(board[i][col]);
                copy[i][col] = 1001;
                game(board, copy, col+1, s, temp);

                copy[i][col] = board[i][col];
                if (temp.size() == board.length) s.soln.add(new ArrayList<>(temp));
                temp.remove(temp.size()-1);
            }
        }

        return;

    }



	public static void main(String[] args) throws IOException {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        String line = "";
	        String content = "";
	        boolean first = true;

	        int N = 0;
	        while ((line = br.readLine()) != null) {
	            if (first) {
	                N = Integer.parseInt(line.trim());
	                first = !first;
	            }
	            else {
	                content += line.trim() + "\n";
	            }

	            if (!br.ready()) break;
	        }
	        br.close();

	        String[] split = content.split("\n");

	        int[][] board = new int[N][N];
	        int[][] copy = new int[N][N];
	        int[] r = new int[N];

	        for (int i = 0; i < split.length; i++) {
	            String[] curr = split[i].split("\\s+");
	            for (int j = 0; j < curr.length; j++) {
	                board[i][j] = Integer.parseInt(curr[j]);
	                copy[i][j] = Integer.parseInt(curr[j]);
	            }
	        }

	        Soln s = new Soln();



	        game(board, copy, 0, s, new ArrayList<Integer>());



	        long minimum = Long.MAX_VALUE;

	        for (int i = 0; i < s.soln.size(); i++) {
	            long minCandidate = 0;
	            ArrayList<Integer> curr = s.soln.get(i);

	            if (curr.size() == board.length) {
	                for (int j = 0; j < curr.size(); j++) {
	                    minCandidate += curr.get(j);
	                }
	            }

	            if (minCandidate < minimum) minimum = minCandidate;

	        }


	        bw.write(minimum + "\n");
	        bw.flush();
	        bw.close();
	    }



	}

