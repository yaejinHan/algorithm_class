import java.io.*;
import java.util.*;

public class party_fee {
	
	   /* used DisjointUnionSets code written in geeksforgeeks as a helper function for this problem
        the code can be found here - https://www.geeksforgeeks.org/disjoint-set-data-structures/     */
    static class DisjointUnionSets {
        int[] rank, parent;
        int n;
        int disjointSetCnt;
        int[] remArr;
        ArrayList<Integer> remainderArr = new ArrayList<Integer>();

        public DisjointUnionSets(int n, String[] arr) {
            rank = new int[n];
            parent = new int[n];
            this.n = n;
            this.disjointSetCnt = n;
            makeSet();

            for (String s : arr) {
                remainderArr.add(Integer.parseInt(s));
            }
        }




        void makeSet() {
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot) return;

            else if (rank[xRoot] < rank[yRoot]) {
                /* in this case the rank of y was greater, so the whole subtree of x (xRoot's tree)
                   is merged to the y subtree (yRoot)
                   that's why we are updating the value of yRoot's index of remainderArr to the added val   */
                parent[xRoot] = yRoot;
                int newVal = remainderArr.get(xRoot) + remainderArr.get(yRoot);

                remainderArr.set(yRoot, newVal);
                remainderArr.set(xRoot, 0);

            } else {

                int newVal = remainderArr.get(xRoot) + remainderArr.get(yRoot);

                remainderArr.set(xRoot, newVal);
                remainderArr.set(yRoot, 0);
                parent[yRoot] = xRoot;
                rank[xRoot] = rank[xRoot] + 1;
            }

            disjointSetCnt--;
        }
    }


	public static void main(String[] args) throws IOException {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = "";
	        String studentContent = "";
	        String friendshipContent = "";
	        int numStudents = 0;
	        int numFriendships = 0;
	        int numStuCnt = 0;

	        boolean first = true;

	        while ((line = br.readLine())!= null) {
	            if (first) {
	                String[] split = line.split("\\s+");
	                numStudents = Integer.parseInt(split[0]);
	                numFriendships = Integer.parseInt(split[1]);
	                first = !first;
	            }

	            else if (numStuCnt < numStudents) {
	                studentContent += line + "\n";
	                numStuCnt++;
	            }

	            else {
	                friendshipContent += line + "\n";
	            }

	            if (!br.ready()) break;
	        }


	        String[] amtArr = studentContent.split("\n");
	        String[] friendshipSplit = friendshipContent.split("\n");
	        DisjointUnionSets s = new DisjointUnionSets(numStudents, amtArr);

	        if (numFriendships == 0) {
	            for (String str: amtArr) {
	                if (Integer.parseInt(str) != 0) {
	                    System.out.println("IMPOSSIBLE");
	                    return;
	                }
	            }
	            System.out.println("POSSIBLE");
	            return;
	        }

	        for (int i = 0; i < friendshipSplit.length; i++) {
	            String[] curr = friendshipSplit[i].split("\\s+");
	            s.union(Integer.parseInt(curr[0]), Integer.parseInt(curr[1]));
	        }

	        ArrayList<Integer> result = s.remainderArr;


	        result.removeAll(Collections.singleton(0));
	        if (!result.isEmpty()) System.out.println("IMPOSSIBLE");
	        else System.out.println("POSSIBLE");





		

	}

}
