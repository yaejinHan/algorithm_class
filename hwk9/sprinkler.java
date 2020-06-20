import java.io.*;
import java.util.*;

public class sprinkler {
	
    static class Sprinkler {
        int radius = 0;
        int position = 0;
        int reaching = 0;

        public Sprinkler(int position, int radius) {
            this.position = position;
            this.radius = radius;
            this.reaching = position + radius;
        }
    }

    static double getIntersectionPoint(Sprinkler curr, int width) {
        double one = Math.pow(curr.radius, 2);
        double two = Math.pow((double)width/2, 2);
        double calc = Math.sqrt(one-two);
        double result = curr.position - calc;
        return curr.position - Math.sqrt((Math.pow(curr.radius, 2)) - (Math.pow(((double)width/2), 2)));
    }



	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        boolean first = true;
        int length = 0;
        int width = 0;
        int numSprinkler = 0;
        ArrayList<Sprinkler> lst = new ArrayList<Sprinkler>(10000);
        int numCnt = 0;

        while ((line = br.readLine()) != null) {
            if (first) {
                String[] split = line.split("\\s+");
                numSprinkler = Integer.parseInt(split[0]);
                length = Integer.parseInt(split[1]);
                width = Integer.parseInt(split[2]);
                first = !first;
            }

            else if (numCnt < numSprinkler) {
                String[] split = line.split("\\s+");
                int position = Integer.parseInt(split[0]);
                int radius = Integer.parseInt(split[1]);
                lst.add(new Sprinkler(position, radius));

                numCnt++;
            }

            if (numCnt == numSprinkler) break;

        }

        Comparator<Sprinkler> customComp = new Comparator<Sprinkler>() {
            @Override
            public int compare(Sprinkler s1, Sprinkler s2) {
                if (s1.reaching > s2.reaching) return -1;
                else if (s1.reaching < s2.reaching) return 1;
                return 0;
            }
        };

        Collections.sort(lst, customComp);

        int currIndx = 0;

        
        if (lst.get(currIndx).reaching <= length || lst.get(currIndx).radius <= (width/2)) {
            System.out.println(-1);
            return;
        }
        
        double currPosition = lst.get(currIndx).reaching;
        double intersectionPnt = getIntersectionPoint(lst.get(currIndx), width);
        ArrayList<Sprinkler> on = new ArrayList<Sprinkler>();
        on.add(lst.get(currIndx));
        currIndx++;



        while (currPosition > 0) {

            boolean firstIntersecting = true;
            Sprinkler toAdd = lst.get(0);

            while (currIndx < lst.size() && lst.get(currIndx).reaching > intersectionPnt) {
                Sprinkler curr = lst.get(currIndx);

                if (firstIntersecting) {
                    if (curr.radius > (width/2)) {
                        toAdd = curr;
                    }
                    firstIntersecting = !firstIntersecting;
                }

                else {
                    if (curr.radius >= toAdd.radius) toAdd = curr;
                    else {
                        if (curr.position - curr.radius < toAdd.position - toAdd.radius) toAdd = curr;
                    }
                }

                currIndx++;
            }


           if (firstIntersecting) {
               System.out.println(-1);
               return;
           }

            on.add(toAdd);
            intersectionPnt = getIntersectionPoint(toAdd, width);
            currPosition = intersectionPnt;
        }


        if (on.get(on.size()-1).position - on.get(on.size()-1).radius >= 0) {
            System.out.println(-1);
            return;
        }


        System.out.println(on.size());

	}

}
