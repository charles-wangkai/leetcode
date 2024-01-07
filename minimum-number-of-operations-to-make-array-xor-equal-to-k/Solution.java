import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums, int k) {
    int result = 0;
    int xor = Arrays.stream(nums).reduce((acc, x) -> acc ^ x).getAsInt();
    while (xor != k) {
      if ((xor & 1) != (k & 1)) {
        ++result;
      }

      xor >>= 1;
      k >>= 1;
    }

    return result;
  }
}