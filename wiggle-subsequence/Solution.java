class Solution {
  public int wiggleMaxLength(int[] nums) {
    return Math.max(search(nums, true), search(nums, false));
  }

  int search(int[] nums, boolean upOrDown) {
    int maxLength = 1;
    for (int i = 1; i < nums.length; ++i) {
      if ((upOrDown && nums[i] > nums[i - 1]) || (!upOrDown && nums[i] < nums[i - 1])) {
        ++maxLength;
        upOrDown = !upOrDown;
      }
    }

    return maxLength;
  }
}
