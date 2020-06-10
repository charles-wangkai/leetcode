public class Solution {
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        int lower = 0;
        int upper = nums.length - 1;
        while (lower <= upper) {
            int middle = (lower + upper) / 2;

            if (nums[middle] < target) {
                result = middle + 1;
                lower = middle + 1;
            } else {
                upper = middle - 1;
            }
        }

        return result;
    }
}
