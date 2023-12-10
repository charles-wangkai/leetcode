import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfGoodPartitions(int[] nums) {
    Map<Integer, Integer> valueToLastIndex = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToLastIndex.put(nums[i], i);
    }

    int result = 1;
    int maxIndex = -1;
    for (int i = 0; i < nums.length - 1; ++i) {
      maxIndex = Math.max(maxIndex, valueToLastIndex.get(nums[i]));

      if (maxIndex == i) {
        result = multiplyMod(result, 2);
      }
    }

    return result;
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}