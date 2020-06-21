import java.io.*;
import java.util.*;

public class yasp {

	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = "";

        while ((line = br.readLine()) != null) {
            if (!br.ready()) break;
        }

        String[] split = line.trim().split("\\s+");

        if (split.length == 0) {
            System.out.println(0);
            return;
        }

        ArrayList<Integer> arr = new ArrayList<>(split.length);
        for (int i = 0; i < split.length; i++) {
            arr.add(Integer.parseInt(split[i]));
        }


        boolean isSorted = true;

        for (int i = 0; i < arr.size()-1; i++) {
            if (arr.get(i) > arr.get(i+1)) {
                isSorted = !isSorted;
                break;
            }
        }

        if (isSorted) {
            System.out.println(0);
            return;
        }

        boolean reverseSorted = true;
        for (int i = 0; i < arr.size()-1; i++) {
            if (arr.get(i) < arr.get(i+1)) {
                reverseSorted = !reverseSorted;
                break;
            }
        }

        if (reverseSorted) {
            System.out.println("1 0");
            return;
        }


        StringBuilder sb = new StringBuilder();
        int maxIndx = arr.size()-1;
        int disregarded = 0;
        int toAppend = 0;
        int diff = 0;
        int currMaxIndx = arr.indexOf(Collections.max(arr));
        int currMaxLastIndx = arr.lastIndexOf(Collections.max(arr));

        if (currMaxIndx != currMaxLastIndx) currMaxIndx = currMaxLastIndx;

        while (maxIndx > 0) {
            isSorted = true;
            if (currMaxIndx == 0) {
                Collections.reverse(arr.subList(0, maxIndx+1));

                for (int i = 0; i < arr.size()-1; i++) {
                    if (arr.get(i) > arr.get(i+1)) {
                        isSorted = !isSorted;
                        break;
                    }
                }
                if (isSorted) {
                    toAppend = disregarded + 1;
                    sb.append((toAppend) + " ");break;
                }


                toAppend = disregarded + 1;
                sb.append((toAppend) + " ");

                if (diff > 0) {
                    maxIndx -= diff;
                    disregarded += diff;
                    diff = 0;
                }

                else {
                    maxIndx--;
                    disregarded++;
                }

                currMaxIndx = arr.indexOf(Collections.max(arr.subList(0, maxIndx+1)));
                currMaxLastIndx = arr.lastIndexOf(Collections.max(arr.subList(0, maxIndx+1)));
                if (currMaxIndx != currMaxLastIndx) {
                    diff = (currMaxLastIndx-currMaxIndx)+1;
                    currMaxIndx = currMaxLastIndx;
                }
            }

            else {
                Collections.reverse(arr.subList(0, (currMaxIndx+1)));

                for (int i = 0; i < arr.size()-1; i++) {
                    if (arr.get(i) > arr.get(i+1)) {
                        isSorted = !isSorted;
                        break;
                    }
                }
                if (isSorted) {
                    toAppend = disregarded + 1;
                    sb.append((toAppend) + " ");
                    break;
                }

                toAppend = maxIndx - currMaxIndx + disregarded + 1;
                sb.append(toAppend + " ");
                currMaxIndx = 0;
            }
        }


        sb.append(0);

        bw.write(sb + "\n");
        bw.flush();
	}

}
