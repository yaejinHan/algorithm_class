import java.io.*;
import java.math.BigInteger;
import java.util.*;




/* referened a code from LeetCode */
public class max_product_subarray {

	
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

        public void close() throws IOException {
            if (d == null) return;
            d.close();
        }
    }

	public static void main(String[] args) throws IOException {
		
		
		
        int n = 0;
        Main.CustomReader c = new Main.CustomReader();
        n = c.nextInt();
        ArrayList<Integer> input = new ArrayList<Integer>();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int negNumCnt = 0;
        int posNumCnt = 0;
        int temp = 0;

        int cnt = 0;
        while (cnt < n) {

            temp = c.nextInt();
            if (temp < 0) {
                negNumCnt++;
            }
            else if (temp > 0) posNumCnt++;
            input.add(temp);



            cnt++;
        }


        if (input.size() == 1) {
            System.out.println(input.get(0));
            return;
        }

        BigInteger[] minArr = new BigInteger[101];
        BigInteger[] maxArr = new BigInteger[101];
        minArr[0] = BigInteger.valueOf(input.get(0));
        maxArr[0] = BigInteger.valueOf(input.get(0));

        BigInteger maxProduct = new BigInteger(String.valueOf(input.get(0)));

        for (int i = 1; i < input.size(); i++) {
            int currNum = input.get(i);

            if (currNum > 0) {
                minArr[i] = minArr[i-1].multiply(BigInteger.valueOf(currNum)).min(BigInteger.valueOf(currNum));
                maxArr[i] = maxArr[i-1].multiply(BigInteger.valueOf(currNum)).max(BigInteger.valueOf(currNum));
            }

            else {
                maxArr[i] = minArr[i-1].multiply(BigInteger.valueOf(currNum)).max(BigInteger.valueOf(currNum));
                minArr[i] = maxArr[i-1].multiply(BigInteger.valueOf(currNum)).min(BigInteger.valueOf(currNum));
            }


            maxProduct = maxProduct.max(maxArr[i]);

        }

        System.out.println(maxProduct);



	}

}
