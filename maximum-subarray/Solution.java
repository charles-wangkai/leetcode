class Solution {
  public int maxSubArray(int[] nums) {
    int result = Integer.MIN_VALUE;
    int sum = 0;
    for (int num : nums) {
      sum += num;
      result = Math.max(result, sum);
      sum = Math.max(sum, 0);
    }

    return result;
  }
}
