import java.util.Arrays;

class Solution {
  public int longestSubsequence(int[] nums) {
    if (Arrays.stream(nums).reduce((acc, x) -> acc ^ x).getAsInt() != 0) {
      return nums.length;
    }

    return Arrays.stream(nums).allMatch(x -> x == 0) ? 0 : (nums.length - 1);
  }
}