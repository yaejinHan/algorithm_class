import java.io.*;
import java.util.*;

public class daily_prize {

	public static void main(String[] args) throws IOException {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));                                        
	       String line = "";                                                                                                
	       String content = "";                                                                                             
	                                                                                                                        
	       while ((line = br.readLine()) != null) {                                                                         
	           content += line + "\n";                                                                                      
	                                                                                                                        
	           if (!br.ready()) break;                                                                                      
	       }                                                    
	       
	                                                                                                                        
	       ArrayList<Integer> arr = new ArrayList<Integer>();                                                               
	                                                                                                                        
	       String[] input = content.split("\n");                                                                            
	                                                                                                                        
	       int numDays = Integer.parseInt(input[0]);                                                                        
	       long prizeCost = 0;                                                                                               
	                                                                                                                        
	       for (int i = 1; i <= numDays; i++) {                                                                             
	           String curr = input[i];                                                                                      
	           String[] temp = curr.split("\\s+");                                                                          
	                                                                                                                        
	           int numForm = Integer.parseInt(temp[0]);                                                                     
	           for (int j = 1; j <= numForm; j++) {                                                                         
	               arr.add(Integer.parseInt(temp[j]));                                                                      
	           }                                                                                                            
	                                                                                                                        
	           int max = Collections.max(arr);                                                                              
	           int min = Collections.min(arr);                                                                              
	                                                                                                                        
	           prizeCost += (max-min);                                                                                      
	           int maxIndx = arr.indexOf(max);                                                                              
	           arr.remove(maxIndx);                                                                                         
	                                                                                                                        
	           int minIndx = arr.indexOf(min);                                                                              
	           arr.remove(minIndx);                                                                                         
	       }                                                                                                                
                                                                                                      
	                                                                                                                        
	       System.out.println(prizeCost);                                                                                   
	                                                                                                                        

	}

}
