class Solution {
  public int longestNiceSubarray(int[] nums) {
    int result = 0;
    int endIndex = -1;
    int sum = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex + 1 != nums.length
          && sum + nums[endIndex + 1] == (sum | nums[endIndex + 1])) {
        ++endIndex;
        sum += nums[endIndex];
      }

      result = Math.max(result, endIndex - beginIndex + 1);
      sum -= nums[beginIndex];
    }

    return result;
  }
}