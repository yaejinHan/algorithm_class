import java.util.*;
import java.io.*;

public class pocket_money {
	
	
	   static class Soln {
	        static int solution = 0;
	    }


	    /*  took the code from GeeksForGeeks for faster I/O
	        the code can be found here - https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/  */
	    static class CustomReader {
	        final private int BUFFER_SIZE = 1 << 16;
	        private DataInputStream d;
	        private byte[] buffer;
	        private int bufferPnt, byteRead;

	        public CustomReader() {
	            d = new DataInputStream(System.in);
	            buffer = new byte[BUFFER_SIZE];
	            bufferPnt = byteRead = 0;
	        }

	        public CustomReader(String fName) throws IOException {
	            d = new DataInputStream(new FileInputStream(fName));
	            buffer = new byte[BUFFER_SIZE];
	            bufferPnt = byteRead = 0;
	        }

	        private void fillBuffer() throws IOException {
	            byteRead = d.read(buffer, bufferPnt = 0, BUFFER_SIZE);
	            if (byteRead == -1) buffer[0] = -1;
	        }

	        private byte read() throws IOException {
	            if (bufferPnt == byteRead) fillBuffer();
	            return buffer[bufferPnt++];

	        }

	        public String readLine() throws IOException {
	            byte[] buf = new byte[64];
	            int count = 0;
	            int c = 0;
	            while ((c = read()) != -1) {
	                if (c == '\n') break;
	                buf[count++] = (byte)c;
	            }
	            return new String(buf, 0, count);
	        }

	        public int nextInt() throws IOException {
	            int ret = 0;
	            byte c = read();
	            while (c <= ' ') c = read();
	            boolean neg = (c == '-');
	            if (neg) c = read();
	            do {
	                ret = ret * 10 + c - '0';
	            } while ((c = read()) >= '0' && c <= '9');


	            if (neg) return -ret;
	            return ret;

	        }

	        public long nextLong() throws IOException {
	            long ret = 0;
	            byte c = read();
	            while (c <= ' ') c = read();
	            boolean neg = (c == '-');
	            if (neg) c = read();
	            do {
	                ret = ret * 10 + c - '0';
	            }
	            while ((c = read()) >= '0' && c <= '9');
	            if (neg) return -ret;
	            return ret;

	        }

	        public double nextDouble() throws IOException {
	            double ret = 0, div = 1;
	            byte c = read();
	            while (c <= ' ') c = read();
	            boolean neg = (c == '-');
	            if (neg) c = read();
	            do {
	                ret = ret * 10 + c - '0';
	            }

	            while ((c = read()) >= '0' && c <= '9');

	            if (c == '.'){
	                while ((c = read()) >= '0' && c <= '9') {
	                    ret += (c-'0')/(div *= 10);
	                }
	            }

	            if (neg) return -ret;
	            return ret;
	        }

	        public void close() throws IOException {
	            if (d == null) return;
	            d.close();
	        }
	    }
	

	public static void main(String[] args) throws IOException {
		
        Main.CustomReader c = new Main.CustomReader();
        double target = c.nextDouble();

        double N = target * 100;
        
        if (Math.ceil(N) - N < N - (Math.floor(N))) N = Math.ceil(N);
        else N = Math.floor(N);
        int x = (int) N;

        int[] coin = {5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        int[][] memo = new int[coin.length][(int)N+1];



        Soln s = new Soln();

        /* referenced code from the internet */
        long[] ans = new long[(int)(N)+1];
        ans[0] = 1;
        for (int i = 0; i < coin.length; i++) {
            for (int j = coin[i]; j <= x; j++) {
                int coinI = coin[i];
                ans[j] += ans[j - coin[i]];

            }
        }

        System.out.printf("%6.2f%17s\n", target, ans[(int)N]);


	}

}
