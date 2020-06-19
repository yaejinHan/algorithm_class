import java.io.*;
import java.util.*;


public class virus {

    static class monk {
        int id = 0;
        int arrivalT = 0;
        int currIndx = 0;
        int numOffices = 0;
        ArrayList<Integer> schedule = new ArrayList<Integer>();
        boolean terminated = false;
        boolean done = false;

        public monk(String[] infoArr, int id) {
            this.arrivalT = Integer.parseInt(infoArr[0]);
            this.numOffices = Integer.parseInt(infoArr[1]);
            this.currIndx = 0;
            for (int i = 2; i <= numOffices + 1; i++) {
                schedule.add(Integer.parseInt(infoArr[i]));
            }
            this.terminated = false;
            this.done = false;
            this.id = id;
        }

    }

    public static ArrayList<monk> getSameArrivingTGroup(int time, ArrayList<monk> monkLst) {
        ArrayList<monk> sameArrivingTGroup = new ArrayList<monk>(monkLst.size());

        for (int i = 0; i < monkLst.size(); i++) {
            monk currMonk = monkLst.get(i);
            if ((currMonk.arrivalT+currMonk.currIndx <= time)) sameArrivingTGroup.add(currMonk);
            if ((currMonk.arrivalT+currMonk.currIndx > time)) break;

        }
        return sameArrivingTGroup;
    }

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        String content = "";

        while ((line = br.readLine()) != null) {
            content += line + "\n";

            if (!br.ready()) break;
        }

        String[] input = content.split("\n");

        String firstLine = input[0];
        String[] firstLineInfo = firstLine.split("\\s+");

        int numMonk = Integer.parseInt(firstLineInfo[0]);
        int numOffices = Integer.parseInt(firstLineInfo[1]);

        ArrayList<monk> monkLst = new ArrayList<monk>(numMonk);

        Comparator<monk> monkComparator = new Comparator<monk>() {
            @Override
            public int compare(monk m1, monk m2) {
                if (m1.arrivalT > m2.arrivalT) return 1;
                else if (m1.arrivalT < m2.arrivalT) return -1;
                return 0;
            }
        };

        Comparator<monk> idComparator = new Comparator<monk>() {
            @Override
            public int compare(monk m1, monk m2) {
                if (m1.id > m2.id) return 1;
                else if (m1.id < m2.id) return -1;
                return 0;
            }
        };
        

        for (int i = 1; i < input.length; i++) {
            String temp = input[i];
            String[] tempInfo = temp.split("\\s+");

            monkLst.add(new monk(tempInfo, i));
        }

        Collections.sort(monkLst, monkComparator);

        ArrayList<Queue<monk>> offices = new ArrayList<>(numOffices);

        for (int i = 0; i < numOffices; i++) {
            Queue<monk> temp = new LinkedList<monk>();
            offices.add(temp);
        }

        int finishT = 0;
        ArrayList<monk> finished = new ArrayList<monk>(numMonk);
        ArrayList<Integer> fT = new ArrayList<Integer>(numMonk);

        int time = 0;


        ArrayList<monk> processing = new ArrayList<monk>(numMonk);
        while (!monkLst.isEmpty()) {

            ArrayList<monk> currArrivingTGroup = getSameArrivingTGroup(time, monkLst);

            if (currArrivingTGroup.size() > 1) Collections.sort(currArrivingTGroup, idComparator);

            while (!currArrivingTGroup.isEmpty()) {
                int n = 0;
                monk currMonk = currArrivingTGroup.get(n);
                if (currMonk.terminated) {
                    currArrivingTGroup.remove(currMonk);
                } else {
                    if (processing.contains(currMonk)) {
                        currArrivingTGroup.remove(currMonk);
                        continue;
                    }
                    int officeLoc = currMonk.schedule.get(currMonk.currIndx) - 1;
                    currMonk.currIndx++;
                    offices.get(officeLoc).add(currMonk);
                    currArrivingTGroup.remove(currMonk);
                    processing.add(currMonk);
                }
            }


            //// popping ///////////////////////////////////////////////////////////////////////////////////////
            for (int a = 0; a < offices.size(); a++) {
                Queue<monk> temp = offices.get(a);
                if (!temp.isEmpty()) {
                    monk doneMonk = temp.poll();
                    processing.remove(doneMonk);

                    if (doneMonk.currIndx >= doneMonk.numOffices) {
                        doneMonk.terminated = true;
                        finishT = time + 1;
                        finished.add(doneMonk);
                        fT.add(finishT);
                        monkLst.remove(doneMonk);
                    }

                }
            }
            time++;

        }




        int lastFinishT = finishT;
        System.out.println(lastFinishT);


	}

}
