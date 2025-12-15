import java.util.Arrays;

class Solution {
  public int absDifference(int[] nums, int k) {
    return Arrays.stream(nums).sorted().skip(nums.length - k).sum()
        - Arrays.stream(nums).sorted().limit(k).sum();
  }
}