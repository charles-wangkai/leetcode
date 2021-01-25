class Solution {
  public boolean kLengthApart(int[] nums, int k) {
    int prevIndex = -1;
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == 1) {
        if (prevIndex != -1 && i - prevIndex - 1 < k) {
          return false;
        }

        prevIndex = i;
      }
    }

    return true;
  }
}
