class Solution {
  public long countSubarrays(int[] nums, long k) {
    long result = 0;
    int endIndex = -1;
    long sum = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1
          && (sum + nums[endIndex + 1]) * (endIndex + 1 - beginIndex + 1) < k) {
        ++endIndex;
        sum += nums[endIndex];
      }

      result += endIndex - beginIndex + 1;

      sum -= nums[beginIndex];
    }

    return result;
  }
}