import java.io.*;
import java.util.*;

public class no_change {
	/*  received help from Chrissy Jeon    */
	
	    static int best = Integer.MAX_VALUE;
	    static int numCoinsUsed = Integer.MAX_VALUE;

	    public static List<List<Integer>> allPossibleSubLists(int[] nums, int target) {
	        List<List<Integer>> list = new ArrayList<>();
	        Arrays.sort(nums);
	        generateSubList(list, new ArrayList<>(), nums, target, 0, target);
	        return list;

	    }

	public static void main(String[] args) throws IOException {
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        boolean first = true;
        int target = 0;
        int numCoins = 0;
        int inputCnt = -1;
        int[] coins = new int[100];

        while ((line = br.readLine()) != null) {
            if (first) {
                target = Integer.parseInt(line.trim());
                first = !first;
            }
            else if (inputCnt == -1) {
                numCoins = Integer.parseInt(line.trim());
                inputCnt++;
            }
            else if (inputCnt < numCoins) {
                coins[inputCnt] = Integer.parseInt(line.trim());
                inputCnt++;
            }

            if (inputCnt == numCoins) break;
        }

        coins = Arrays.copyOfRange(coins, 0, numCoins);

        List<List<Integer>> result = allPossibleSubLists(coins,target);

        System.out.println(best + " " + numCoinsUsed);



	}
	
    static void generateSubList(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remaining, int curr, int target){
        if(remaining == 0) {
            int sum = target + Math.abs(remaining);
            if (sum < best) {
                list.add(new ArrayList<>(tempList));
                best = sum;
                numCoinsUsed = tempList.size();
            }
            else if (sum == best) {
                list.add(new ArrayList<>(tempList));
                if (numCoinsUsed > tempList.size()) numCoinsUsed = tempList.size();
            }
        }
        else if(remaining < 0) {

            int sum = target + Math.abs(remaining);
            if (sum < best) {
                list.add(new ArrayList<>(tempList));
                best = sum;
                numCoinsUsed = tempList.size();
            }
            else if (sum == best) {
                list.add(new ArrayList<>(tempList));
                if (numCoinsUsed > tempList.size()) numCoinsUsed = tempList.size();
            }
        }

        else{
            for(int i = curr; i < nums.length; i++){
                tempList.add(nums[i]);
                generateSubList(list, tempList, nums, remaining - nums[i], i + 1, target);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

