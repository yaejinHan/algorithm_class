import java.io.*;
import java.util.*;

public class find_root {
	
    static class rootFind {

        public double rootFind(double start, double end, double a, double b, double c, double d, double e, double desiredVal, boolean first) {


            if (first) {

                double testOne = a * Math.pow(Math.E, (-1) * 0) + b * Math.sin(0) + c * Math.cos(0) + d * Math.tan(0) + e * Math.pow(0,2);
                double testTwo = a * Math.pow(Math.E, (-1) * 1) + b * Math.sin(1) + c * Math.cos(1) + d * Math.tan(1) + e * Math.pow(1,2);

                if (Math.abs(testOne - desiredVal) < 0.00001) return 0;
                else if (Math.abs(testTwo - desiredVal) < 0.00001) return 1;

                double upperRange = 0;
                double lowerRange = 0;

                if (testTwo > testOne) {
                    upperRange = testTwo;
                    lowerRange = testOne;
                }

                else {
                    upperRange = testOne;
                    lowerRange = testTwo;
                }

                if (desiredVal > upperRange || desiredVal < lowerRange) {
                    return -1;
                }

                first = !first;

            }

            double x = (start+end) -  (start+end)/2;
            
           
            double computedVal = a * Math.pow(Math.E, (-1)*x) + b * Math.sin(x) + c * Math.cos(x) + d * Math.tan(x) + e * Math.pow(x, 2);

            if (Math.abs(computedVal - desiredVal) < 0.00001) return x;

            else {

                if (a > e) {
                    if (computedVal < desiredVal) {
                        end = x;
                        return rootFind(start, end, a, b, c, d, e, desiredVal, false);
                    }

                    else {
                        start = x;
                        return rootFind(start, end, a, b, c, d, e, desiredVal, false);
                    }

                }

                else {
                    if (computedVal < desiredVal) {
                        start = x;
                        return rootFind(start, end, a, b, c, d, e, desiredVal, false);
                    }
                    else {
                        end = x;
                        return rootFind(start, end, a, b, c, d, e, desiredVal,false);
                    }
                }

            }

        }
    }


	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = "";
        double[] input = new double[6];


        while ((line = br.readLine()) != null) {

            String[] split = line.split("\\s+");
            for (int i = 0; i < split.length; i++) {
                input[i] = Double.parseDouble(split[i]);
            }

            if (!br.ready()) break;
        }

        double rootFound = new rootFind().rootFind(0.00001, 1, input[0], input[1], input[2], input[3], input[4], (-1) * input[5],true);
        if (rootFound >= 0) System.out.printf("%.4f\n", rootFound);
        else System.out.println("No solution");


		

	}

}
