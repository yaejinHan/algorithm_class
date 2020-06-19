import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class alberts_dream {

	public static void main(String[] args) throws IOException {
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = "";
	        String content = "";

	        while ((line = br.readLine())!= null) {

	            content += line + " ";
	            if (!br.ready()) break;
	        }
	        br.close();
	        
	        String finalContent = content.replaceAll("[*0-9]", " ");

	        String[] input = finalContent.split("\\s+ |\\W+");

	        TreeSet dict = new TreeSet(String.CASE_INSENSITIVE_ORDER);
	        dict.addAll(Arrays.asList(input));

	        Iterator it = dict.iterator();

	        String first = (String) it.next();
	        
        	if (!first.equals("")) System.out.println(first);

	        while (it.hasNext()) {
	            String word = (String)it.next();
	            System.out.println(word.toLowerCase());
	        }
	    }

}
