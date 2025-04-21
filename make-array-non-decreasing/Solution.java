class Solution {
  public int maximumPossibleSize(int[] nums) {
    int result = 0;
    int max = -1;
    for (int num : nums) {
      if (num >= max) {
        max = num;
        ++result;
      }
    }

    return result;
  }
}