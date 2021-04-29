class Solution {
  public int[] searchRange(int[] nums, int target) {
    return new int[] {search(nums, target, true), search(nums, target, false)};
  }

  int search(int[] nums, int target, boolean leftOrRight) {
    int result = -1;
    int lower = 0;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;

      if (nums[middle] > target) {
        upper = middle - 1;
      } else if (nums[middle] < target) {
        lower = middle + 1;
      } else {
        result = middle;

        if (leftOrRight) {
          upper = middle - 1;
        } else {
          lower = middle + 1;
        }
      }
    }

    return result;
  }
}
