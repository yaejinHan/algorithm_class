/* used the DisjointUnionSets found in geeksforgeeks website for the helper function of this problem
code can be found here - https://www.geeksforgeeks.org/disjoint-set-data-structures/  */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.*;


public class calvins_stars {
	
    static boolean sameConstellation(star s1, star s2, double d) {
        double distance = Math.sqrt(((s2.Y - s1.Y) * (s2.Y - s1.Y)) + ((s2.X - s1.X) * (s2.X - s1.X)));
        return (distance <= d);
    }


    static void groupConstellation(ArrayList<star> sameGroup, star s2, double dist, DisjointUnionSets s) {

        for (int i = 0; i < sameGroup.size(); i++) {
            if (sameConstellation(sameGroup.get(i), s2, dist)) {
                s.union(sameGroup.get(i).id, s2.id);
            }
        }

    }

    /* this is the code gotten from geeksforgeeks as mentioned above */
    static class DisjointUnionSets {
        int[] rank, parent;
        int n;
        int disjointSetCnt;

        public DisjointUnionSets(int n) {
            rank = new int[n];
            parent = new int[n];
            this.n = n;
            this.disjointSetCnt = n;
            makeSet();
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
                parent[xRoot] = yRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot] = rank[xRoot] + 1;
            }

            disjointSetCnt--;
        }
    }

    static class star {
        int id = 0;
        double X = 0;
        double Y = 0;

        star(double X, double Y) {
            this.X = X;
            this.Y = Y;
        }
    }

	    
		public static void main(String[] args) throws IOException {
 
		       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		        String line = "";
		        String content = "";

		        while ((line = br.readLine()) != null) {
		            content += line + "\n";

		            if (!br.ready()) break;
		        }


		        String[] input = content.split("\\s+");

		        int numStars = Integer.parseInt(input[0]);

		        if (numStars == 0) {
		            System.out.print(numStars);
		            return;
		        }
		        if (numStars == 1) {
		            System.out.print(numStars);
		            return;
		        }


		        double d = Double.parseDouble(input[1]);

		        ArrayList<star> stars = new ArrayList<star>(numStars);


		        for (int i = 2; i <= numStars * 2; i += 2) {
		            double X = Double.parseDouble(input[i]);
		            double Y = Double.parseDouble(input[i + 1]);
		            star s = new star(X,Y);
		            stars.add(s);

		        }

		        for (int i = 0; i < stars.size(); i++) {
		            stars.get(i).id = i;
		        }

		        DisjointUnionSets s = new DisjointUnionSets(numStars);

		        int indx = 0;

		        for (int i = 0; i < stars.size(); i++) {
		            star curr = stars.get(i);
		            for (int j = i+1; j < stars.size(); j++) {
		                star toCompare = stars.get(j);
		                if (sameConstellation(curr, toCompare, d)) {
		                    s.union(curr.id, toCompare.id);
		                }
		            }
		        }
		        System.out.print(s.disjointSetCnt);
		    }

}
