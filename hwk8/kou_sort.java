import java.io.*;
import java.util.*;

public class kou_sort {
	
	
	/*  implemented merge sort after class discussion for optimized performance
	    referenced GeeksForGeeks page to solve this problem
            the code can be found here - https://www.geeksforgeeks.org/counting-inversions/   */
	
	
	
    static class Sorting {



        long sortEachHalf(int[] arr, int start, int end) {

            long inversionCnt = 0;

            if (start < end) {
                int mid = (start+end)/2;

                /* when we are computing our inversion counts,
                   we want to first get the inversion counts
                   from the first half of the array,
                   then from the second half of the array
                   and from the whole array by merging those
                   two sorted first half and the second half of the array    */

                /* this is getting the inversion count from the first half of the array   */
                inversionCnt += sortEachHalf(arr, start, mid);

                /* this is getting the inversion count from the second half of the array   */
                inversionCnt += sortEachHalf(arr, mid+1, end); 

                /* this is the last step of merging those two and getting
                   the inversion count that we see during the process
                   of merging                                           */
                inversionCnt += merge(arr, start, mid, end);


            }

            return inversionCnt;
        }




        long merge(int[] arr, int start, int mid, int end) {

            int[] firstHalf = new int[(mid-start)+1];
            int[] secondHalf = new int[end-mid];


            for (int i = 0; i < firstHalf.length; i++) {
                firstHalf[i] = arr[start+i];
            }

            for (int i = 0; i < secondHalf.length; i++) {
                secondHalf[i] = arr[mid+1+i];
            }

            int firstHalfArrIndx = 0;
            int secondHalfArrIndx = 0;
            int arrIndx = start;
            long numSwaps = 0;

            while (firstHalfArrIndx < firstHalf.length && secondHalfArrIndx < secondHalf.length) {
                /* no inversion in this case,
                   this case is sorted    */
                if (firstHalf[firstHalfArrIndx] <= secondHalf[secondHalfArrIndx]) {
                    arr[arrIndx] = firstHalf[firstHalfArrIndx];
                    arrIndx++;
                    firstHalfArrIndx++;
                }

                else {
                    /* in this case, since the prior indexed element is
                       greater than the latter indexed element,
                       there was a swap needed and thus
                       this is one of the inversion counts that we need to count
                       since in this sorting of merge sort,
                       left and right are sorted,
                       when arr[firstHalfArrIndx] >= arr[secondHalfArrIndx],
                       we know that all the other remaining elements of
                       the first half of the array starting from the
                       current firstHalfArrIndx would be greater
                       than the middle (mid) of the total array
                       that we are looking at at the moment           */
                    arr[arrIndx] = secondHalf[secondHalfArrIndx];
                    arrIndx++;
                    secondHalfArrIndx++;
                    int midPoint = mid+1;
                    int greaterElements = start + firstHalfArrIndx;
                    numSwaps += midPoint - greaterElements;
                }




            }

            /* in this case, the second half of the array elements were
               exhausted faster so that we need to fill in
               the first half of the array using a while loop     */
            while (firstHalfArrIndx < firstHalf.length) {
                arr[arrIndx] = firstHalf[firstHalfArrIndx];
                arrIndx++;
                firstHalfArrIndx++;
            }

            /* this case is the opposite of the case above,
               the first half of the array elements were exhausted faster
               and thus elements left in the second half array
               are filled in to the arr using while loop     */
            while (secondHalfArrIndx < secondHalf.length) {
                arr[arrIndx] = secondHalf[secondHalfArrIndx];
                arrIndx++;
                secondHalfArrIndx++;
            }

            return numSwaps;

        }

    }



	public static void main(String[] args) throws IOException {
		
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = "";
	        String content = "";
	        int[] arr = new int[500000];
	        int indx = 0;
	        boolean first = true;

	        while ((line = br.readLine()) != null) {
	            if (first) {
	                first = !first;
	            }

	            else {
	                arr[indx] = Integer.parseInt(line);
	                indx++;
	            }


	            if (!br.ready()) break;
	        }



	        long numSwapsNeeded = new Sorting().sortEachHalf(arr, 0, indx-1);
	        System.out.println(numSwapsNeeded);

	}

}
