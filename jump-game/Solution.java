class Solution {
  public boolean canJump(int[] nums) {
    int maxIndex = 0;
    for (int i = 0; ; ++i) {
      if (maxIndex >= nums.length - 1) {
        return true;
      }
      if (i == maxIndex + 1) {
        return false;
      }

      maxIndex = Math.max(maxIndex, i + nums[i]);
    }
  }
}
