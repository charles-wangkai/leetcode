import java.util.Arrays;

class Solution {
  public long maxTotalValue(int[] nums, int k) {
    return (long) (Arrays.stream(nums).max().getAsInt() - Arrays.stream(nums).min().getAsInt()) * k;
  }
}