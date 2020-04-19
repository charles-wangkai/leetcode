public class Solution {
    public int search(int[] A, int target) {
        int lower = 0;
        int upper = A.length - 1;
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            if (A[middle] == target) {
                return middle;
            } else if (A[lower] <= A[middle]) {
                if (target >= A[lower] && target < A[middle]) {
                    upper = middle - 1;
                } else {
                    lower = middle + 1;
                }
            } else {
                if (target > A[middle] && target <= A[upper]) {
                    lower = middle + 1;
                } else {
                    upper = middle - 1;
                }
            }
        }

        return -1;
    }
}
