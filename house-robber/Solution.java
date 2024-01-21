class Solution {
  public int rob(int[] nums) {
    int prev2 = 0;
    int prev1 = 0;
    for (int num : nums) {
      int current = prev2 + num;

      prev2 = prev1;
      prev1 = Math.max(prev2, current);
    }

    return prev1;
  }
}
