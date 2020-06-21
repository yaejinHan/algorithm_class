import java.io.*;
import java.util.*;


/*    used sieve method from hw3    */
public class math_hw {

	public static void main(String[] args) throws IOException {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = "";
	        int start = 0;
	        int end = 0;

	        while ((line = br.readLine()) != null) {


	            if (!br.ready()) break;
	        }

	        String[] split = line.split("\\s+");
	        start = Integer.parseInt(split[0]);
	        if (start == 0) start = 1;
	        end = Integer.parseInt(split[1]);


	        ArrayList<Integer> pLst = new ArrayList<Integer>();

	        ArrayList<Boolean> pFactor = new ArrayList<Boolean>(Arrays.asList(new Boolean[(int)Math.sqrt(end)+1]));
	        ArrayList<Boolean> pArr = new ArrayList<Boolean>(Arrays.asList(new Boolean[end-start+1]));

	        Collections.fill(pFactor, true);
	        Collections.fill(pArr, true);

	        pFactor.set(0, false);
	        pFactor.set(1, false);

	        if (start == end || end <= 2) {
	            System.out.println(-1);
	            return;
	        }

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

	        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
	        for (int i = 0; i < pLst.size()-1; i++) {
	            int gap = pLst.get(i+1) - pLst.get(i);

	            if (!h.containsKey(gap)) {
	                h.put(gap, 1);
	            }

	            else {
	                int frequencyCnt = h.get(gap);
	                frequencyCnt++;
	                h.replace(gap, frequencyCnt);
	            }
	        }

	        int frequencyCnt = Collections.max(h.values());

	        /* the line below was found on page - https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
	           referenced the line to find the key of the maximum value in the map      */
	        int key = Collections.max(h.entrySet(), Map.Entry.comparingByValue()).getKey();

	        h.remove(key, frequencyCnt);

	        if (h.containsValue(frequencyCnt)) {
	            System.out.println(-1);
	            return;
	        }

	        System.out.println(key);


	}

}
