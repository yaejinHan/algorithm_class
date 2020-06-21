import java.io.*;
import java.util.*;

public class jills_bicycle {
	
    /* took the code from GeeksForGeeks for faster I/O
       the code can be found here - https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/   */
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

    
    
    static int findValidElement(int indx, int[] arr) {
        while (arr[indx] < 0 && indx < arr.length) {
            indx++;
            if (indx == arr.length-1 && arr[indx] < 0) return -1;
        }
        return indx;
    }
    
    
    public static int findposIndx(int indx, int[] arr) {
        boolean pos = false;
        int ans = 0;
        for (int i = indx; i < arr.length; i++) {
            if (i != indx && arr[i] > 0) {
                ans = i;
                pos = !pos;
                break;
            }
        }

        if (!pos) return -1;
        else return ans;
    }

	public static void main(String[] args) throws IOException {
        Main.CustomReader c = new Main.CustomReader();
        int N = c.nextInt();

        int[] safetyVal = new int[N];

        int cnt = 0;
        int indx = 0;
        boolean first = true;

        while (cnt < N) {
            int val = c.nextInt();
            if (val > 0 && first) {
                indx = cnt;
                first = !first;
            }
            safetyVal[cnt] = val;
            cnt++;
        }

        if (first) {
            System.out.println("No safe streets along this route.");
            return;
        }


        long globalMax = Long.MIN_VALUE;
        long currMax = 0;

        for (int i = indx; i < safetyVal.length; i++) {
            int val = safetyVal[i];
            if (currMax + val < 0) currMax = 0;
            else {
                currMax += val;
                if (globalMax < currMax) globalMax = currMax;
            }
        }




        if (globalMax < currMax) globalMax = currMax;
        System.out.println(globalMax);



	}

}
