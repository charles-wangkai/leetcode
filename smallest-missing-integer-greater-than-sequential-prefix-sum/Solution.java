import java.util.Arrays;

class Solution {
  public int missingInteger(int[] nums) {
    int prefixSum = 0;
    for (int i = 0; i < nums.length && (i == 0 || nums[i] - nums[i - 1] == 1); ++i) {
      prefixSum += nums[i];
    }

    for (int i = prefixSum; ; ++i) {
      int i_ = i;
      if (Arrays.stream(nums).allMatch(x -> x != i_)) {
        return i;
      }
    }
  }
}