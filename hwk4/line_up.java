import java.io.*;
import java.util.*;


public class line_up {

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";
        boolean firstTime = true;


        while ((line = br.readLine()) != null) {
            content += line + "\n";
            if (line.equals("Shutdown")) break;
        }


        // i'll be storing elements on the left and groupNum on the right
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        Map<Integer, Queue> map = new LinkedHashMap<Integer, Queue>();
        String[] input = content.split("\n");

        int num = Integer.parseInt(input[0]);


        for (int i = 1; i <= num; i++) {
            String[] inputLine = input[i].split(" ");
            for (int j = 1; j <= Integer.parseInt(inputLine[0]); j++) {
                h.put(Integer.parseInt(inputLine[j]), Integer.parseInt(inputLine[1]));
            }
        }

        Queue<Integer> result = new LinkedList<Integer>();


        for (int i = num + 1; i < input.length - 1; i++) {

            String[] inputLine = input[i].split(" ");

            if (inputLine.length == 2) {
                int groupIDNum = h.get(Integer.parseInt(inputLine[1]));

                if (!map.keySet().contains(groupIDNum)) {
                    Queue<Integer> q = new LinkedList<Integer>();
                    q.add(Integer.parseInt(inputLine[1]));
                    map.put(groupIDNum, q);

                } else if (map.keySet().contains(groupIDNum)) {
                    Queue<Integer> q = map.get(groupIDNum);

                    q.add(Integer.parseInt(inputLine[1]));
                }
            } else {

                int polled = 0;
                int groupNum = 0;

                Iterator itr = map.entrySet().iterator();
                if (itr.hasNext()) {
                    Map.Entry element = (Map.Entry) itr.next();
                    LinkedList lst = (LinkedList) element.getValue();
                    int key = (Integer)element.getKey();

                    if (lst.isEmpty()) {
                        element = (Map.Entry) itr.next();
                    } else {
                        System.out.println(lst.getFirst());
                        lst.removeFirst();

                        if (lst.isEmpty()) {
                            map.remove(key, map.get(key));

                        }


                    }
                }
            }
        }
    }
}
