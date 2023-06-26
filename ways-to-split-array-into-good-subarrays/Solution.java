import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfGoodSubarraySplits(int[] nums) {
    if (Arrays.stream(nums).allMatch(x -> x == 0)) {
      return 0;
    }

    int result = 1;
    int lastOneIndex = -1;
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == 1) {
        if (lastOneIndex != -1) {
          result = multiplyMod(result, i - lastOneIndex);
        }

        lastOneIndex = i;
      }
    }

    return result;
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
