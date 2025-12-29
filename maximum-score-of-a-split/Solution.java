import java.util.Arrays;

class Solution {
  public long maximumScore(int[] nums) {
    long result = Long.MIN_VALUE;
    long prefixSum = Arrays.stream(nums).asLongStream().sum();
    long suffixMin = Long.MAX_VALUE;
    for (int i = nums.length - 2; i >= 0; --i) {
      prefixSum -= nums[i + 1];
      suffixMin = Math.min(suffixMin, nums[i + 1]);

      result = Math.max(result, prefixSum - suffixMin);
    }

    return result;
  }
}