/* used the idea of sieving as learned in class for this problem */

import java.util.*;

public class prime_gap {

	public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        int min = 0, max = 0;


        int start = sc.nextInt();
        if (start == 0) start = 1;

        int end = sc.nextInt();

        int p1Indx = 0, p3Indx = 0;


        ArrayList<Integer> pLst = new ArrayList<Integer>();


        /* pFactor storing all prime factors */
        ArrayList<Boolean> pFactor = new ArrayList<Boolean>(Arrays.asList(new Boolean[(int)Math.sqrt(end)+1]));
        ArrayList<Boolean> pArr = new ArrayList<Boolean>(Arrays.asList(new Boolean[end-start+1]));


        /* initially setting all numbers as prime */
        Collections.fill(pFactor, true);
        Collections.fill(pArr, true);


        /* 0 and 1 are not prime */
        pFactor.set(0, false);
        pFactor.set(1, false);

        /* sieve starts */
        for (int i = 0; i <= Math.sqrt(end); i++) {
            if (pFactor.get(i)) {
                for (int j = i+i; j < pFactor.size(); j += i) {
                    pFactor.set(j, false);
                }
            }
        }


        for (int i = pFactor.indexOf(true); i < pFactor.size(); i++) {
            if (pFactor.get(i)) {
                long indx = (long)Math.ceil(start*1.0/i)*i;
                for (long j = indx; j <= end; j+=i) {
                    pArr.set((int)(j-start), false);
                }

                if ((long)(Math.ceil((start*1.0)/i))== 1) {
                    pArr.set((int)(indx-start), true);
                }
            }
        }
        for (int i = 0; i < pArr.size(); i++) {
            if (pArr.get(i) && i+start !=1) {
                pLst.add(i+start);
            }
        }

        if (pLst.size() <= 1) {
            System.out.println(-1);
            return;
        }


        for (int i = 0; i < pLst.size() - 1; i++) {
            int gap = pLst.get(i + 1) - pLst.get(i);

            if (min == 0) {
                p1Indx = i;
                min = gap;
                p3Indx = i;
                max = gap;
            } else {
                if (gap < min) {
                    p1Indx = i;
                    min = gap;
                } else if (gap > max) {
                    p3Indx = i;
                    max = gap;
                }
            }
        }

        System.out.println(pLst.get(p1Indx) + " " + pLst.get(p1Indx + 1) + " " + pLst.get(p3Indx) + " " + pLst.get(p3Indx + 1));
    }

}
