class Solution {
  public int maxAscendingSum(int[] nums) {
    int result = 0;
    int sum = 0;
    int prev = -1;
    for (int num : nums) {
      if (num > prev) {
        sum += num;
      } else {
        sum = num;
      }

      result = Math.max(result, sum);
      prev = num;
    }

    return result;
  }
}
