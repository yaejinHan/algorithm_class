import java.util.*;
import java.io.*;

public class deficit_cycle {
	
    /*  took the code from GeeksForGeeks for faster I/O
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
		
        Main.CustomReader c = new Main.CustomReader();
        int N = c.nextInt();
        int M = c.nextInt();
        ArrayList<ArrayList<int[]>> edgeList = new ArrayList<>();

        int u = 0;
        int v = 0;
        int w = 0;

        for (int i = 0; i < N; i++) {
            edgeList.add(new ArrayList<>());
        }


        int cnt = 0;
        while (cnt < M) {
            int[] temp = new int[2];
            u = c.nextInt();

            temp[0] = c.nextInt();  // v
            temp[1] = c.nextInt();  // w

            edgeList.get(u).add(temp);
            cnt++;

        }


        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        long original = 0;

        boolean minimized = false;

        for (int k = 0; k < N-1; k++) {
            minimized = false;

            for (int i = 0; i < edgeList.size(); i++) {
                ArrayList<int[]> curr = edgeList.get(i);
                if (dist[i] == Long.MAX_VALUE) continue;


                for (int j = 0; j < curr.size(); j++) {
                    int[] currEdge = curr.get(j);

                    v = currEdge[0];
                    w = currEdge[1];


                    original = dist[v];
                    dist[v] = Math.min(dist[v], dist[i]+w);

                    if (dist[v] != original && !minimized) minimized = !minimized;


                }
            }

            if (!minimized) break;
        }

        if (!minimized) {
            System.out.println("not possible");
            return;
        }

        minimized = false;

        for (int i = 0; i < edgeList.size(); i++) {
            ArrayList<int[]> curr = edgeList.get(i);

            for (int j = 0; j < curr.size(); j++) {
                int[] currEdge = curr.get(j);

                v = currEdge[0];
                w = currEdge[1];

                original = dist[v];
                dist[v] = Math.min(dist[v], dist[i]+w);

                if (original != dist[v] && !minimized) minimized = !minimized;
            }
        }

        if (minimized) System.out.println("possible");


        else System.out.println("not possible");





	}

}
