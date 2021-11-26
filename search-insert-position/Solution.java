class Solution {
  public int searchInsert(int[] nums, int target) {
    int result = nums.length;
    int lower = 0;
    int upper = nums.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;

      if (nums[middle] >= target) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}
