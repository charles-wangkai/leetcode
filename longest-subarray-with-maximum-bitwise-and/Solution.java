import java.util.Arrays;

class Solution {
  public int longestSubarray(int[] nums) {
    int max = Arrays.stream(nums).max().getAsInt();

    int result = 0;
    int length = 0;
    for (int num : nums) {
      if ((num & max) == max) {
        ++length;
        result = Math.max(result, length);
      } else {
        length = 0;
      }
    }

    return result;
  }
}