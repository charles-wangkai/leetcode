import java.util.Arrays;

class Solution {
  public int singleNumber(int[] nums) {
    int[] bits = new int[32];
    for (int num : nums) {
      for (int i = 0; i < bits.length; ++i) {
        if (((num >> (bits.length - 1 - i)) & 1) == 1) {
          bits[i] = (bits[i] + 1) % 3;
        }
      }
    }

    return Arrays.stream(bits).reduce(0, (acc, x) -> acc * 2 + x);
  }
}
