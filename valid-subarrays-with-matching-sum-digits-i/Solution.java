class Solution {
  public int countValidSubarrays(int[] nums, int x) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      long sum = 0;
      for (int j = i; j < nums.length; ++j) {
        sum += nums[j];
        if (isValid(x, sum)) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean isValid(int x, long sum) {
    String s = String.valueOf(sum);

    return s.charAt(0) - '0' == x && s.charAt(s.length() - 1) - '0' == x;
  }
}