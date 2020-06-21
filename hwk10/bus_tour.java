import java.util.*;
import java.io.*;

public class bus_tour {
	

    static class bestRouteInfo {
        int i = 0;
        int j = 0;
        long niceness = 0;

        public bestRouteInfo(int i, int j, long niceness) {
            this.i = i;
            this.j = j;
            this.niceness = niceness;
        }

    }

    static int findValidElement(int[] stops, int indx) {

        while (stops[indx] < 0 && indx < stops.length) {
            indx++;

            if (indx == stops.length-1 && stops[indx] < 0) return -1;
        }

        return indx;
    }


    static void updateSolution(int i, int j, long sum, bestRouteInfo b) {



        if (b.niceness < sum) {
            b.niceness = sum;
            b.i = i;
            b.j = j+1;
        }

        else if (b.niceness == sum) {
            int bRideLength = b.j - b.i;
            int candidateRideLength = j - i;

            if (bRideLength < candidateRideLength) {
                b.i = i;
                b.j = j+1;
            }

            else if (bRideLength == candidateRideLength) {
                if (i < b.i) {
                    b.i = i;
                    b.j = j+1;
                }
            }
        }

    }


	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        boolean first = true;
        int numStops = 0;
        int[] stops = new int[20001];
        int busStopCnt = 0;
        boolean firstPositive = false;
        int indx = 0;

        while ((line = br.readLine()) != null) {

            if (first) {
                numStops = Integer.parseInt(line.trim());
                first = !first;
            }

            else if (busStopCnt < numStops-1) {
                stops[busStopCnt] = Integer.parseInt(line.trim());
                if (stops[busStopCnt] > 0 && !firstPositive) {
                    firstPositive = !firstPositive;
                }
                busStopCnt++;
            }


            if (!br.ready()) break;
        }

        stops = Arrays.copyOfRange(stops, 0, busStopCnt);

        if (!firstPositive) {
            System.out.println("Yet another overrated tourist destination");
            return;
        }

        indx = findValidElement(stops, 0);


        int i = -1;
        int j = 0;
        long sum = 0;


        bestRouteInfo b = new bestRouteInfo(i, j, sum);


        while (indx < numStops-1) {
            int curr = stops[indx];

            if (sum + curr >= 0) {
                if (i == -1) i = indx;
                sum += curr;
                if (sum >= b.niceness) updateSolution(i, indx, sum, b);
                indx++;
            }

            else {
                indx = findValidElement(stops, indx);
                if (indx == -1) break;
                sum = 0;
                i = -1;
            }
        }


        System.out.println("The nicest part of Y1 is between stops " + (b.i+1) + " and " + (b.j+1));




	}

}
