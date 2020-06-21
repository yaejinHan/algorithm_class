import java.io.*;
import java.util.*;

public class date_overflow {
	
    static class Computer {
        int yi = 0;
        int ai = 0;
        int bi = 0;

        public Computer(int yi, int ai, int bi) {
            this.yi = yi;
            this.ai = ai;
            this.bi = bi;
        }

    }

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        boolean first = true;
        String content = "";
        int n = 0;

        while ((line = br.readLine()) != null) {
            if (first) {
                n = Integer.parseInt(line.trim());
                first = !first;
            }

            else {
                content += line.trim() + "\n";
            }

            if (!br.ready()) break;
        }

        String[] split = content.split("\n");

        ArrayList<Computer> comp = new ArrayList<>();

        for (int i = 0; i < split.length; i++) {
            String[] temp = split[i].split("\\s+");
            int yi = Integer.parseInt(temp[0]);
            int ai = Integer.parseInt(temp[1]);
            int bi = Integer.parseInt(temp[2]);

            comp.add(new Computer(yi, ai, bi));
        }


        if (comp.size() == 1) {
            System.out.println(comp.get(0).yi);
            return;
        }

        int indx = 0;
        int match = -1;
        BitSet b = new BitSet(10001);
        int startPnt = 0;
        int toAdd = 0;
        int toSet = 0;

        while (indx < comp.size()) {

            Computer curr = comp.get(indx);
            boolean matched = false;
            startPnt = curr.yi;
            toAdd = curr.bi - curr.ai;
            toSet = startPnt;

            if (indx == 0) {
                while (toSet < 10000) {
                    b.set(toSet);
                    toSet += toAdd;
                }
            }

            else if (indx == 1) {
                while (toSet < 10000) {
                    if (b.get(toSet) == true) {
                        match = toSet;
                        break;
                    }

                    toSet += toAdd;
                }
                if (match == -1) {
                    System.out.println(-1);
                    return;
                }
            }

            else {
                while (toSet <= match) {
                    if (toSet == match) {
                        matched = !matched;
                        break;
                    }
                    toSet += toAdd;
                }

                if (!matched) {
                    System.out.println(-1);
                    return;
                }
            }

            indx++;

        }

        System.out.println(match);


	}

}
