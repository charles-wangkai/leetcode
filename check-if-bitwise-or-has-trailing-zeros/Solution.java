import java.util.Arrays;

class Solution {
  public boolean hasTrailingZeros(int[] nums) {
    return Arrays.stream(nums).filter(x -> x % 2 == 0).count() >= 2;
  }
}