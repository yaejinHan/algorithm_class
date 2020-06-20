mport java.util.*;
import java.io.*;

public class badminton_class {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        boolean first = true;
        int numM = 0;
        int numF = 0;
        int MCnt = 0;
        int FCnt = 0;
        ArrayList<Integer> M = new ArrayList<Integer>();
        ArrayList<Integer> F = new ArrayList<Integer>();

        while ((line = br.readLine())!= null) {

            if (first) {
                String[] split = line.split("\\s+");
                numM = Integer.parseInt(split[0]);
                numF = Integer.parseInt(split[1]);
                first = !first;
            }
            else if (MCnt < numM) {
                M.add(Integer.parseInt(line));
                MCnt++;
            }

            else if (FCnt < numF){
                F.add(Integer.parseInt(line));
                FCnt++;
            }

            if (FCnt == numF) break;

        }


        if (numM == numF || numM < numF) {
            System.out.println(0);
            return;
        }

        Collections.sort(M, Collections.reverseOrder());
        Collections.sort(F, Collections.reverseOrder());

        List<Integer> unMatched = M.subList(F.size(), M.size());

        System.out.println(unMatched.size() + " " + Collections.min(unMatched));



	}

}
