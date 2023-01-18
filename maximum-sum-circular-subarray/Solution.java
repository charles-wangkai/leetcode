import java.util.Arrays;

class Solution {
  public int maxSubarraySumCircular(int[] nums) {
    int maxSum = computeMaxSum(nums);
    if (maxSum < 0) {
      return maxSum;
    }

    return Math.max(maxSum, Arrays.stream(nums).sum() - computeMinSum(nums));
  }

  int computeMaxSum(int[] a) {
    int sum = 0;
    int maxSum = Integer.MIN_VALUE;
    for (int ai : a) {
      sum += ai;
      maxSum = Math.max(maxSum, sum);
      sum = Math.max(0, sum);
    }

    return maxSum;
  }

  int computeMinSum(int[] a) {
    return -computeMaxSum(Arrays.stream(a).map(x -> -x).toArray());
  }
}
