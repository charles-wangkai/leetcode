public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftIndex = search(nums, target, true);
        if (leftIndex < 0) {
            return new int[] { -1, -1 };
        }
        int rightIndex = search(nums, target, false);
        return new int[] { leftIndex, rightIndex };
    }

    int search(int[] nums, int target, boolean leftOrRight) {
        int lower = 0;
        int upper = nums.length - 1;
        int index = -1;
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            if (nums[middle] > target) {
                upper = middle - 1;
            } else if (nums[middle] < target) {
                lower = middle + 1;
            } else {
                index = middle;
                if (leftOrRight) {
                    upper = middle - 1;
                } else {
                    lower = middle + 1;
                }
            }
        }
        return index;
    }
}
