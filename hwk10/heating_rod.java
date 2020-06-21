import java.io.*;
import java.util.*;


public class heating_rod {

    static double findAngle(double arcLength, double desiredval, double start, double end) {

    	double currAngle = (start+end)/2;
    	

        double computedVal = (arcLength/currAngle) * Math.sin(currAngle/2);
        if (Math.abs(computedVal - desiredval) < 0.000000001) return currAngle;

        else if (computedVal > desiredval) start = currAngle;
        else if (computedVal < desiredval) end = currAngle;

        return findAngle(arcLength, desiredval, start, end);
    }

    
    
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        double displacement = 0;
        double L = 0;
        double T = 0;
        double C = 0;
        double angle = 0;
        double radius = 0;
        double x = 0;


        while ((line = br.readLine()) != null) {
            if (line.equals("")) continue;
            if(!br.ready()) break;
        }

        String[] split = line.split("\\s+");

        L = Double.parseDouble(split[0]);
        T = Double.parseDouble(split[1]);
        C = Double.parseDouble(split[2]);

        
       
        Double arcLength = L * (1 + T * C);




        if (L - arcLength == 0) {
            System.out.println("0.000");
            return;
        }


        
        angle = findAngle(arcLength, L / 2, 0, 3.14159);


        radius = arcLength / angle;
        
        x = Math.sqrt((Math.pow(radius, 2) - Math.pow(L / 2, 2)));
       
        displacement = radius - x;

        System.out.printf("%.3f\n", displacement);
        
	}

}
