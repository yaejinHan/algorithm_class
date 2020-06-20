import java.util.*;
import java.io.*;


public class polar_bear {
    /* used DisjointUnionSets code written in geeksforgeeks as the helper function for this problem
       the code can be found here - https://www.geeksforgeeks.org/disjoint-set-data-structures/ */
    public static class DisjointUnionSets {
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

    
    public static class Island {
        int n = 0;
        int m = 0;
        int elevation = 0;
        int id = 0;

        public Island(int n, int m, int elevation, int id) {
            this.n = n;
            this.m = m;
            this.elevation = elevation;
            this.id = id;
        }

        public Island(int elevation, int id) {
            this.elevation = elevation;
            this.id = id;
        }
    }

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = "";
        String content = "";
        boolean first = true;
        int row = 0;
        int col = 0;
        int rowCnt = 0;
        int id = 0;
        Hashtable<List<Integer>, Island> h = new Hashtable<List<Integer>, Island>();
        ArrayList<Island> arr = new ArrayList<>();
        Comparator<Island> islandComparator = new Comparator<Island>() {
            @Override
            public int compare(Island i1, Island i2) {
                if (i1.elevation > i2.elevation) return -1;
                else if (i1.elevation < i2.elevation) return 1;
                return 0;
            }
        };

        while ((line = br.readLine()) != null) {

            if (first) {
                String[] split = line.split(" ");
                row = Integer.parseInt(split[0]);
                col = Integer.parseInt(split[1]);
                rowCnt = 0;
                first = !first;
            }

            else if (rowCnt < row) {
                String[] split = line.split(" ");
                for (int i = 0; i < split.length; i++) {
                    List<Integer> coordinate = Arrays.asList(rowCnt, i);
                    Island temp = new Island(rowCnt, i, Integer.parseInt(split[i]), id);
                    h.put(coordinate, temp);
                    arr.add(temp);
                    id++;
                }
                rowCnt++;
            }

            else {
                content += line + "\n";
            }

            if (!br.ready()) break;

        }


        Collections.sort(arr, islandComparator);
        DisjointUnionSets s = new DisjointUnionSets(arr.size());

        String[] split = content.split("\\s+");
        ArrayList<Integer> elevationQ = new ArrayList<Integer>();
        for (int i = 1; i < split.length; i++) {
            elevationQ.add(Integer.parseInt(split[i]));
        }



        int prevElevIndx = 0;
        int islandCnt = 0;
        String result = "";
        boolean firstT = true;
        int currElevCnt = 0;


        Collections.sort(elevationQ, Collections.reverseOrder());

        while (!elevationQ.isEmpty()) {
            int currElevation = elevationQ.get(0);
            elevationQ.remove(0);

            if (arr.get(prevElevIndx).elevation > currElevation) {
                for (int i = prevElevIndx; i < arr.size(); i++) {
                    Island currIsland = arr.get(i);
                    if (currIsland.elevation > currElevation) {
                        currElevCnt++;
                        int n = currIsland.n;
                        int m = currIsland.m;
                        List<Integer> crd1 = Arrays.asList(n-1, m);
                        List<Integer> crd2 = Arrays.asList(n,m-1);
                        List<Integer> crd3 = Arrays.asList(n, m+1);
                        List<Integer> crd4 = Arrays.asList(n+1, m);

                        if (h.get(crd1) != null && h.get(crd1).elevation > currElevation) {
                            s.union(currIsland.id, h.get(crd1).id);
                        }
                        if (h.get(crd2) != null && h.get(crd2).elevation > currElevation) {
                            s.union(currIsland.id, h.get(crd2).id);
                        }
                        if (h.get(crd3) != null && h.get(crd3).elevation > currElevation) {
                            s.union(currIsland.id, h.get(crd3).id);
                        }
                        if (h.get(crd4) != null && h.get(crd4).elevation > currElevation) {
                            s.union(currIsland.id, h.get(crd4).id);
                        }

                    }
                    else {
                        prevElevIndx = i;
                        break;
                    }
                    if (currElevCnt > arr.size()) currElevCnt = arr.size();

                }
            }

            int disjointCnt = s.disjointSetCnt;
            int neglectedCells = arr.size() - currElevCnt;
            islandCnt = (disjointCnt-neglectedCells);
            if (firstT) {
                result = Integer.toString(islandCnt);
                firstT = !firstT;
            }

            else result = islandCnt + " " + result;




        }
        bw.write(result + "\n");
        bw.flush();

		
		
		

	}

}
