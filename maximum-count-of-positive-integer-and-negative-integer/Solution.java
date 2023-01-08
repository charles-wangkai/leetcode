import java.util.Arrays;

class Solution {
  public int maximumCount(int[] nums) {
    return Math.max(
        (int) Arrays.stream(nums).filter(x -> x > 0).count(),
        (int) Arrays.stream(nums).filter(x -> x < 0).count());
  }
}
