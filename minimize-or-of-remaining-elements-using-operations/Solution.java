// https://leetcode.com/problems/minimize-or-of-remaining-elements-using-operations/solutions/4637066/easy-solution-with-explanation-checking-bits-must-watch/

class Solution {
  static final int BIT_NUM = 30;

  public int minOrAfterOperations(int[] nums, int k) {
    int result = 0;
    for (int i = BIT_NUM - 1; i >= 0; --i) {
      int target = result | ((1 << i) - 1);

      int count = 0;
      int current = (1 << BIT_NUM) - 1;
      for (int num : nums) {
        current &= num;
        if ((current | target) == target) {
          ++count;
          current = (1 << BIT_NUM) - 1;
        }
      }

      if (nums.length - count > k) {
        result += 1 << i;
      }
    }

    return result;
  }
}