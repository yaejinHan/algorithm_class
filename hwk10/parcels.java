import java.io.*;
import java.util.*;

public class parcels {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int[] box = new int[6];

        while ((line = br.readLine()) != null) {
            if (line.equals("")) continue;

            String[] split = line.split(" ");
            for (int i = 0; i < split.length; i++) {
                box[i] = Integer.parseInt(split[i]);
            }

            if (!br.ready()) break;
        }

        double totalSizeNeeded = 0;
        int currQuantity = 0;
        double numNeeded = 0;
        double leftSpace = 0;
        double boxNeeded = 0;
        int firstComplement = 0;
        int secondComplement = 0;

        for (int i = box.length-1; i >= 0; i--) {
            currQuantity = box[i];
            if (i+1 == 6) numNeeded += currQuantity;

            else if (i+1 == 5 && currQuantity > 0) {
                numNeeded += currQuantity;
                totalSizeNeeded = (25 * currQuantity);
                leftSpace = (36 * currQuantity) - totalSizeNeeded;
                if (leftSpace == 0) continue;

                secondComplement = box[0];
                if (secondComplement > 0) {
                    if (secondComplement > leftSpace) box[0] -= leftSpace;
                    else box[0] = 0;
                }
            }

            else if (i+1 == 4 && currQuantity > 0) {
                numNeeded += currQuantity;
                totalSizeNeeded = (16 * currQuantity);
                leftSpace = (36 * currQuantity) - totalSizeNeeded;
                if (leftSpace == 0) continue;

                firstComplement = box[1];
                secondComplement = box[0];
                if (firstComplement > 0) {
                    if (firstComplement > (5*currQuantity)) {
                        box[1] -= (5*currQuantity);
                        leftSpace -= (4*(5*currQuantity));
                    }
                    else {
                        box[1] = 0;
                        leftSpace -= (4*firstComplement);
                    }
                }

                if (leftSpace > 0 && secondComplement > 0) {
                    if (secondComplement > leftSpace) box[0] -= leftSpace;
                    else box[0] = 0;
                }
            }

            else if (i+1 == 3 && currQuantity > 0) {
                totalSizeNeeded = 9 * currQuantity;
                boxNeeded = Math.ceil(totalSizeNeeded/36);
                numNeeded += boxNeeded;
                leftSpace = (36*boxNeeded) - totalSizeNeeded;
                if (leftSpace == 0) continue;

                firstComplement = box[1];
                secondComplement = box[0];

                if (leftSpace == 27 && firstComplement > 0) {
                    if (firstComplement > 5) {
                        box[1] -= 5;
                        leftSpace -= (4*(5));
                    }
                    else {
                        box[1] = 0;
                        leftSpace -= (4*firstComplement);
                    }
                }
                if (leftSpace == 18 && firstComplement > 0) {
                    if (firstComplement >= 3) {
                        box[1] -= 3;
                        leftSpace -= (4*3);
                    }
                    else {
                        box[1] = 0;
                        leftSpace -= (4*firstComplement);
                    }
                }
                if (leftSpace == 9 && firstComplement > 0) {
                    if (firstComplement >= 1) {
                        box[1] -= 1;
                        leftSpace -= (4*1);
                    }
                    else {
                        box[1] = 0;
                        leftSpace -= (4*firstComplement);
                    }
                }

                if (leftSpace == 0) continue;

                if (secondComplement > leftSpace) box[0] -= leftSpace;
                else box[0] = 0;

            }

            else if (i+1 == 2 && currQuantity > 0) {
                totalSizeNeeded = (4 * currQuantity);
                boxNeeded = Math.ceil(totalSizeNeeded/36);
                numNeeded += boxNeeded;
                leftSpace = (36 * boxNeeded) - totalSizeNeeded;
                if (leftSpace == 0) continue;

                secondComplement = box[0];
                if (secondComplement > 0) {
                    if (secondComplement > leftSpace) box[0] -= leftSpace;
                    else box[0] = 0;
                }
            }

            else if (i+1 == 1 && currQuantity > 0) {
                totalSizeNeeded = (1*currQuantity);
                boxNeeded = Math.ceil(totalSizeNeeded/36);
                numNeeded += boxNeeded;
            }

        }

        System.out.println((int)numNeeded);

	}

}
