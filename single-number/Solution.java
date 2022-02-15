import java.util.Arrays;

class Solution {
  public int singleNumber(int[] nums) {
    return Arrays.stream(nums).reduce((x, y) -> x ^ y).getAsInt();
  }
}
