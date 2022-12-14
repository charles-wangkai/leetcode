class Solution {
  public int rob(int[] nums) {
    int prev3 = 0;
    int prev2 = 0;
    int prev1 = 0;
    for (int num : nums) {
      int current = Math.max(prev3, prev2) + num;

      prev3 = prev2;
      prev2 = prev1;
      prev1 = current;
    }

    return Math.max(prev2, prev1);
  }
}
