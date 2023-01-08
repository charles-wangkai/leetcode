import java.util.Arrays;

class Solution {
  public int xorBeauty(int[] nums) {
    return Arrays.stream(nums).reduce((x, y) -> x ^ y).getAsInt();
  }
}
