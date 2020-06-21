import java.io.*;
import java.util.*;

public class desolate_num {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        while ((line = br.readLine())!= null) {
            if (line.equals("")) continue;

            if (!br.ready()) break;

        }

        String[] split = line.trim().split("\\s+");
        int A = Integer.parseInt(split[0]);
        int B = Integer.parseInt(split[1]);

        if (A == 0 || A == 1) {
            if (A == 0) System.out.println(A);
            if (A == 1) System.out.println(A);
            return;
        }

        String str = "";

        for (int i = 0; i < A; i++) {
            str += "1";
        }

        if (B == 0) {
            System.out.println(Long.parseLong(str, 2));
            return;
        }

        int lookedAt = 0;
        int BUsed = 0;
        int loc = 0;
        boolean first = true;
        boolean isEven = true;
        String tmp1 = "";
        String tmp2 = "";

        if (A % 2 != 0) isEven = false;




        while (BUsed < B) {

            if (first) {
                if (isEven || !isEven && Math.ceil((double)A/2) > B) {
                    loc = str.indexOf("1");
                    if (loc < 0) break;
                    tmp1 = str.substring(0, loc+1);
                    tmp2 = str.substring(loc+1);
                    str = tmp1+"0"+tmp2;
                    lookedAt = loc+1;
                }
                else {
                    loc = str.indexOf("11");
                    if (loc < 0) break;
                    tmp1 = str.substring(0, loc+2);
                    tmp2 = str.substring(loc+2);
                    str = tmp1+"0"+tmp2;
                    lookedAt = loc+3;
                }

                first = !first;
                BUsed++;
                if (lookedAt >= str.length()-1) break;
            }

            else {
                loc = str.indexOf("11", lookedAt);
                if (loc < 0) break;
                tmp1 = str.substring(0, loc+2);
                tmp2 = str.substring(loc+2);
                str = tmp1+"0"+tmp2;
                lookedAt = loc+3;
                if (lookedAt >= str.length()-1) break;
                BUsed++;

            }


        }

        long ans = Long.parseLong(str, 2);
        System.out.println(ans);

    }

	
}
