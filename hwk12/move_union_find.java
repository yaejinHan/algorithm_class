import java.io.*;
import java.util.*;

public class move_union_find {
	
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

	
    /* received help from Chrissy Jeon   */
    static class DisjointSetUnionBySize {
        int n;
        int[] size;
        long[] sum;

        public DisjointSetUnionBySize(int n) {
            this.n = n;
            this.size = new int[n];
            this.sum = new long[n];
            for (int i = 0; i < n; i++) {
                sum[i] = i;
                size[i] = -1;
            }
        }

        int find(int x) {
            if (size[x] > 0) return size[x] = find(size[x]);

            return x;
        }

        void union(int a, int b) {
            int aRoot = find(a);
            int bRoot = find(b);

            if (aRoot == bRoot) return;

            if (-size[aRoot] > -size[bRoot]) {
                size[aRoot] += size[bRoot];
                size[bRoot] = aRoot;
                sum[aRoot] += sum[bRoot];
                sum[bRoot] = 0;
            }

            else {
                size[bRoot] += size[aRoot];
                size[aRoot] = bRoot;
                sum[bRoot] += sum[aRoot];
                sum[aRoot] = 0;
            }
        }

        void move(int a, int b) {

            int aRoot = find(a);
            int bRoot = find(b);

            if (aRoot == bRoot) return;

            if (aRoot == a && size[aRoot] == -1) {
                union(a,b);
            }

            else if (aRoot == a && size[aRoot] < -1) {
                int newRoot = 0;
                boolean first = true;

                for (int i = 0; i < size.length; i++) {
                    int currRoot = size[i];
                    if (currRoot == aRoot) {

                        if (first) {
                            newRoot = i;
                            size[i] = size[aRoot] + 1;
                            sum[i] = sum[aRoot] - aRoot;
                            first = !first;
                        }

                        else size[i] = newRoot;
                    }
                }

                size[bRoot] -= 1;
                sum[bRoot] += aRoot;
                size[aRoot] = bRoot;
                sum[aRoot] = 0;

            }

            else  {
                size[aRoot] += 1;
                sum[aRoot] -= a;
                size[bRoot] -= 1;
                sum[bRoot] += a;
                size[a] = bRoot;
            }
        }


    }

    



	public static void main(String[] args) throws IOException {

		

      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] input;


      int N = 0;
      int M = 0;


      Main.CustomReader c = new Main.CustomReader();
      N = c.nextInt();
      M = c.nextInt();
      List<String[]> lst = new ArrayList<String[]>();
      int cnt = 0;
      while (cnt  < M) {
          input = c.readLine().split("\\s+");
          lst.add(input);
          cnt++;
      }

      c.close();


      DisjointSetUnionBySize s = new DisjointSetUnionBySize(N+1);
      String cmd = "";
      int aRoot = 0;
      long sum = 0;
      int size = 0;
      int a = 0;
      int b = 0;

      for (int i = 0; i < lst.size(); i++) {
          String[] currCmd = lst.get(i);
          cmd = currCmd[0];
          if (!cmd.equals("3")) {
              a = Integer.parseInt(currCmd[1]);
              b = Integer.parseInt(currCmd[2]);
          }

          else a = Integer.parseInt(currCmd[1]);

          if (cmd.equals("1")) s.union(a,b);
          else if (cmd.equals("2")) s.move(a,b);
          else {
              aRoot = s.find(a);
              size = s.size[aRoot];
              sum = s.sum[aRoot];

              bw.write((-size) + " " + (sum) + "\n");
              bw.flush();


          }

      }

      bw.close();


	}
	
	
}



