import java.io.*;
import java.util.*;

public class nucleic_acid {
	
	   static class S {
	        int id = 0;
	        String str = "";
	        int inversionCnt = 0;

	        public S(int id, String str) {
	            this.id = id;
	            this.str = str;
	        }
	    }

	    static class helper {

	        public boolean isSorted(S curr) {
	            String str = curr.str;
	            if (str.contains("A") && str.charAt(0) != 'A') return false;
	            else if (!str.contains("A") && str.contains("C") && str.charAt(0) != 'C') return false;
	            else if (!str.contains("A") && str.contains("C") && str.contains("G") && str.charAt(0) != 'G') return false;
	            for (int i = 0; i < str.length()-1; i++) {
	                if (str.charAt(i) > str.charAt(i+1)) return false;
	            }

	            return true;
	        }

	        public int Count(S curr) {
	            int inversionCnt = 0;
	            String str = curr.str;
	            ArrayList<String> forSorting = new ArrayList<>();
	            String[] toProcess = str.split("");
	            for (int i = 0; i < toProcess.length; i++) {
	                String toAdd = toProcess[i]+i;
	                forSorting.add(toAdd);
	            }

	            Comparator<String> comp = new Comparator<String>() {
	                @Override
	                public int compare(String s1, String s2) {
	                    if (s1.charAt(0) > s2.charAt(0)) return 1;
	                    else if (s1.charAt(0) < s2.charAt(0)) return -1;
	                    else {
	                        if (Integer.parseInt(s1.substring(1)) > Integer.parseInt(s2.substring(1))) return 1;
	                        else return -1;
	                    }
	                }
	            };

	            Collections.sort(forSorting, comp);

	            for (int i = 0; i < forSorting.size(); i++) {
	                if (!forSorting.get(i).contains("A")) {
	                    char currLetter = forSorting.get(i).charAt(0);
	                    int indx = Integer.parseInt(forSorting.get(i).substring(1));
	                    for (int j = i-1; j >= 0; j--) {
	                        char letterCompare = forSorting.get(j).charAt(0);
	                        int indxCompare = Integer.parseInt(forSorting.get(j).substring(1));
	                        if (currLetter > letterCompare && indx < indxCompare) inversionCnt++;
	                    }
	                }
	            }



	            return inversionCnt;

	        }
	    }



	public static void main(String[] args) throws IOException {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = "";
	        String content = "";
	        boolean first = true;
	        int strLen = 0;
	        int numStr = 0;

	        while ((line = br.readLine()) != null) {
	            if (first) {
	                String[] split = line.split("\\s+");
	                strLen = Integer.parseInt(split[0]);
	                numStr = Integer.parseInt(split[1]);
	                first = !first;
	            }
	            else {
	                content += line + "\n";
	            }

	            if (!br.ready()) break;
	        }

	        String[] input = content.split("\n");
	        ArrayList<S> inputStr = new ArrayList<S>(numStr);
	        for (int i = 0; i < input.length; i++) {
	            inputStr.add(new S(i, input[i]));
	        }

	        helper h = new helper();
	        ArrayList<S> result = new ArrayList<S>();
	        Comparator<S> customComparator = new Comparator<S>() {
	            @Override
	            public int compare(S s1, S s2) {
	                if (s1.inversionCnt > s2.inversionCnt) return 1;
	                else if (s1.inversionCnt < s2.inversionCnt) return -1;
	                else {
	                    if (s1.id > s2.id) return 1;
	                    else if (s1.id < s2.id) return -1;
	                }
	                return 0;
	            }
	        };

	        for (int i = 0; i < inputStr.size(); i++) {
	            S curr = inputStr.get(i);
	            if (h.isSorted(curr)) {
	                curr.inversionCnt = 0;
	                result.add(curr);
	            }
	            else {
	                int inversionCnt = h.Count(curr);
	                curr.inversionCnt = inversionCnt;
	                result.add(curr);
	            }

	        }

	        Collections.sort(result, customComparator);

	        for (int i = 0; i < result.size()-1; i++) {
	            System.out.println(result.get(i).str);
	        }
	        
	        System.out.println(result.get(result.size()-1).str);





		

	}

}
