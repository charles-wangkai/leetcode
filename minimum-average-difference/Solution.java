import java.util.Arrays;

class Solution {
  public int minimumAverageDifference(int[] nums) {
    int result = -1;
    int minAvgDiff = Integer.MAX_VALUE;
    long total = Arrays.stream(nums).asLongStream().sum();
    long leftSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      leftSum += nums[i];
      int leftAvg = (int) (leftSum / (i + 1));
      int rightAvg = (i == nums.length - 1) ? 0 : (int) ((total - leftSum) / (nums.length - i - 1));
      int avgDiff = Math.abs(leftAvg - rightAvg);
      if (avgDiff < minAvgDiff) {
        minAvgDiff = avgDiff;
        result = i;
      }
    }

    return result;
  }
}