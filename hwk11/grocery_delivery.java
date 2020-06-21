import java.io.*;
import java.util.*;

public class grocery_delivery {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean first= true;
        String line = "";
        int N = 0;
        int W = 0;
        int[] box = new int[2];

        while ((line = br.readLine()) != null) {
            if (first) {
                String[] split = line.split("\\s+");
                N = Integer.parseInt(split[0]);
                W = Integer.parseInt(split[1]);
                first = !first;
            }
            else {

            }
            if (!br.ready()) break;
        }

        StringBuilder sb = new StringBuilder();
        int orderCnt = 0;
        String[] orderSize = line.split("\\s+");
        box[0] = W;
        box[1] = W;

        for (int i = 0; i < N; i++) {

            int currSize = Integer.parseInt(orderSize[i]);
            if (currSize > box[0] && currSize > box[1]) break;

            if (box[0] <= box[1]) {
                if (currSize <= box[0]) {
                    box[0] -= currSize;
                    sb.append("1st\n");
                }

                else {
                    box[1] -= currSize;
                    sb.append("2nd\n");
                }
            }

            else {
                if (currSize <= box[1]) {
                    box[1] -= currSize;
                    sb.append("2nd\n");
                }

                else {
                    box[0] -= currSize;
                    sb.append("1st\n");
                }
            }

            orderCnt++;



        }


        System.out.println(orderCnt);
        System.out.println(sb);



	}

}
