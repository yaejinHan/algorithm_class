import java.io.*;
import java.util.*;

public class dejavu_center {
	
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

	public static void main(String[] args) throws IOException {
		
		
        Main.CustomReader c = new Main.CustomReader();
        int N = c.nextInt();
        int C = c.nextInt();

        if (N == 1) {
            System.out.println(1);
            return;
        }
        
        
        int start = 1;
        int end = N;
        ArrayList<Integer> pLst = new ArrayList<Integer>();

        ArrayList<Boolean> pFactor = new ArrayList<Boolean>(Arrays.asList(new Boolean[(int)Math.sqrt(end)+1]));
        ArrayList<Boolean> pArr = new ArrayList<Boolean>(Arrays.asList(new Boolean[end-start+1]));

        Collections.fill(pFactor, true);
        Collections.fill(pArr, true);

        pFactor.set(0, false);
        pFactor.set(1, false);

        for (int i = 0; i <= Math.sqrt(end); i++) {
            if (pFactor.get(i)) {
                for (int j = i+i; j < pFactor.size(); j += i) {
                    pFactor.set(j, false);
                }
            }
        }


        for (int i = pFactor.indexOf(true); i < pFactor.size(); i++) {
            if (pFactor.get(i)) {
                long indx = (long)Math.ceil(start*1.0/i)*i;
                for (long j = indx; j <= end; j+=i) {
                    pArr.set((int)(j-start), false);
                }

                if ((long)(Math.ceil((start*1.0)/i))== 1) {
                    pArr.set((int)(indx-start), true);
                }
            }
        }
        for (int i = 0; i < pArr.size(); i++) {
            if (i == 0) pLst.add(1);
            if (pArr.get(i) && i+start !=1) {
                pLst.add(i+start);
            }
        }





        StringBuilder sb = new StringBuilder();


        if (pLst.size() % 2 != 0) {

            if (2 * C - 1 <= pLst.size()) {
                int cnt = 0;
                int diff = pLst.size() - (2* C - 1);
                int indx = diff/2;
                while (cnt < (2 * C -1)) {
                    sb.append(pLst.get(indx) + " ");
                    indx++;
                    cnt++;
                }
            }

            else {
                for (int i = 0; i < pLst.size(); i++) {
                    sb.append(pLst.get(i)+ " ");
                }
            }

        }

        else {
            if (2 * C <= pLst.size()) {
                int cnt = 0;
                int diff = pLst.size()- (2 * C);
                int indx = diff/2;
                while (cnt < (2 * C)) {
                    sb.append(pLst.get(indx) + " ");
                    indx++;
                    cnt++;
                }
            }

            else {
                for (int i = 0; i < pLst.size(); i++) {
                    sb.append(pLst.get(i) + " ");
                }
            }
        }


        String str = sb.toString().trim();


      System.out.println(N + " " + C + ": " + str);

	}

}
