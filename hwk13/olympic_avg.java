import java.io.*;
import java.util.*;

public class olympic_avg {
	
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
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        n = c.nextInt();

	        ArrayList<ArrayList<Long>> container = new ArrayList<>();
	        ArrayList<Long> remaining = new ArrayList<>();
	        String[] input;
	        long k;

	        int cnt = 0;
	        while (cnt < n) {
	            input = c.readLine().split("\\s+");
	            ArrayList<Long> temp = new ArrayList<>(input.length);
	            k = Long.parseLong(input[0]);

	            for (int i = 1; i <= k; i++) {
	                temp.add(Long.parseLong(input[i]));
	            }
	            container.add(temp);
	            cnt++;
	        }


	        long max = 0;
	        long min = 0;
	        StringBuilder sb = new StringBuilder();


	        for (int i = 0; i < container.size(); i++) {
	            ArrayList<Long> temp = container.get(i);
	            remaining.addAll(temp);

	            max = Collections.max(remaining);
	            min = Collections.min(remaining);
	            remaining.remove(Long.valueOf((max)));
	            remaining.remove(Long.valueOf(min));

	            if (i != container.size()-1) sb.append((max-min) + "\n");

	            else sb.append((max-min));

	        }





	        bw.write(sb + "\n");
	        bw.flush();
	        bw.close();


	}

}
