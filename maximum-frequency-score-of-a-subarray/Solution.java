import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int maxFrequencyScore(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = 0; i < k - 1; ++i) {
      updateMap(valueToCount, nums[i], 1);
    }

    int result = -1;
    int score =
        valueToCount.keySet().stream()
            .mapToInt(value -> powMod(value, valueToCount.get(value)))
            .reduce(this::addMod)
            .orElse(0);
    for (int i = k - 1; i < nums.length; ++i) {
      updateMap(valueToCount, nums[i], 1);
      score =
          addMod(
              score,
              addMod(
                  (valueToCount.get(nums[i]) == 1)
                      ? 0
                      : -powMod(nums[i], valueToCount.get(nums[i]) - 1),
                  powMod(nums[i], valueToCount.get(nums[i]))));

      result = Math.max(result, score);

      score =
          addMod(
              score,
              addMod(
                  -powMod(nums[i - k + 1], valueToCount.get(nums[i - k + 1])),
                  (valueToCount.get(nums[i - k + 1]) == 1)
                      ? 0
                      : powMod(nums[i - k + 1], valueToCount.get(nums[i - k + 1]) - 1)));
      updateMap(valueToCount, nums[i - k + 1], -1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int powMod(int base, int exponent) {
    int result = 1;
    while (exponent != 0) {
      if ((exponent & 1) != 0) {
        result = multiplyMod(result, base);
      }

      exponent >>= 1;
      base = multiplyMod(base, base);
    }

    return result;
  }
}
