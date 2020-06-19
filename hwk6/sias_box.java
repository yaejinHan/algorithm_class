import java.util.*;
import java.io.*;

public class sias_box {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";

        while ((line= br.readLine()) != null) {
            content += line + " ";

            if (!br.ready()) break;
        }

        String[] input = content.split("\\s+");
        int numLines = Integer.parseInt(input[0]);

        if (numLines == 1) {
            int instruc = Integer.parseInt(input[1]);
            if (instruc == 1) {
                System.out.println("not sure");
            }
            else if (instruc == 2){
                System.out.println("impossible");
            }
            return;
        }

        Stack<Integer> stack = new Stack<Integer>();
        Queue<Integer> queue= new LinkedList<Integer>();
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());

        int indx = 0;
        String id = "";

        for (int i = 1; i < numLines*2; i+=2) {
            int instruc = Integer.parseInt(input[i]);
            int val = Integer.parseInt(input[i+1]);

            if (instruc == 1) {
                stack.push(val);
                queue.add(val);
                pQ.add(val);
            }

            else {
                if (i == 1) {
                    System.out.println("impossible");
                    return;
                }
                int[] arr = new int[3];
                int s = stack.pop();
                int q = queue.poll();
                int pq = pQ.poll();

                arr[0] = s;
                arr[1] = q;
                arr[2] = pq;

                if (arr[0] == val) {
                    id += "0";
                }
                if (arr[1] == val) {
                    id += "1";
                }
                if (arr[2] == val) {
                    id += "2";
                }

                if (id.length() == 0) {
                    System.out.println("impossible");
                    return;
                }

                indx = i;
                break;
            }
        }
        if (indx == 0) {
            System.out.println("not sure");
            return;
        }

        for (int i = indx+2; i < numLines*2; i+=2) {
            int instruc = Integer.parseInt(input[i]);
            int val = Integer.parseInt(input[i+1]);

            if (id.length() == 1) {
                if (instruc == 1) {
                    if (id.equals("0")) stack.push(val);
                    if (id.equals("1")) queue.add(val);
                    if (id.equals("2")) pQ.add(val);
                }
                else {
                    if (id.equals("0")) {
                        if (stack.size() == 0 || stack.pop() != val) {
                            System.out.println("impossible");
                            return;
                        }
                    }

                    if (id.equals("1")) {
                        if (queue.size() == 0 || queue.poll() != val) {
                            System.out.println("impossible");
                            return;
                        }
                    }

                    if (id.equals("2")) {
                        if (pQ.size() == 0 || pQ.poll() != val) {
                            System.out.println("impossible");
                            return;
                        }
                    }
                }

            }

            if (id.length() > 1) {
                if (instruc == 1) {
                    if (id.contains("0")) stack.push(val);
                    if (id.contains("1")) queue.add(val);
                    if (id.contains("2")) pQ.add(val);
                }

                else {
                    if (id.contains("0")) {
                        if (stack.size() == 0) {
                            System.out.println("impossible");
                            return;
                        }
                        int s = stack.pop();
                        if (s != val) id = id.replaceAll("0", "");
                    }

                    if (id.contains("1")) {
                        if (queue.size() == 0) {
                            System.out.println("impossible");
                            return;
                        }
                        int q = queue.poll();
                        if (q != val) id = id.replaceAll("1", "");
                    }
                    if (id.contains("2")) {
                        if (pQ.size() == 0) {
                            System.out.println("impossible");
                            return;
                        }
                        int pq = pQ.poll();
                        if (pq != val) id = id.replaceAll("2", "");
                    }
                }
            }

            if (id.length() == 0){
                System.out.println("impossible");
                return;
            }
        }

        if (id.length() > 1) {
            System.out.println("not sure");
        }

        else if (id.length() == 0) {
            System.out.println("impossible");
        }

        else if (id.length() == 1){
            if (id.equals("0")) System.out.println("stack");
            if (id.equals("1")) System.out.println("queue");
            if (id.equals("2")) System.out.println("priority queue");
        }



    }
}
