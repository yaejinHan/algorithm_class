import java.util.*;
import java.io.*;

public class ways_to_add {
	
    static long waysToAdd(int N, int K, long[][] memo, int indx) {

        long val = 0;
        if (N == 0 || K == 0) return 1;

        if (memo[K][N] != 0) return memo[K][N];

        for (int i = indx; i <= N; i++) {
            if (N - i < 0) continue;

            val += waysToAdd(N-i, K-1, memo, indx);
            memo[K-1][N-i] = waysToAdd(N-i, K-1, memo, indx);
        }


        return memo[K][N] = (val % 1000000007);


    }


	public static void main(String[] args) throws IOException{
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = "";
        int N = 0;
        int K = 0;

        while ((line = br.readLine()) != null) {
            if (line.equals("")) continue;
            if (!br.ready()) break;
        }

        String[] split = line.split("\\s+");

        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]) - 1;


        long[][] memo = new long[K+1][N+1];

        long ways =  waysToAdd(N, K, memo, 0);
        long answer = ways % 1000000007;


        System.out.println(ways);
    }

		

}
