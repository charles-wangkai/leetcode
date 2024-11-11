import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumOfGoodSubsequences(int[] nums) {
    int[] leftWayNums = buildWayNums(nums);
    int[] rightWayNums = reverse(buildWayNums(reverse(nums)));

    return IntStream.range(0, nums.length)
        .map(i -> multiplyMod(nums[i], multiplyMod(leftWayNums[i], rightWayNums[i])))
        .reduce(this::addMod)
        .getAsInt();
  }

  int[] buildWayNums(int[] values) {
    int[] result = new int[values.length];
    Map<Integer, Integer> valueToWayNum = new HashMap<>();
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          addMod(
              1,
              addMod(
                  valueToWayNum.getOrDefault(values[i] - 1, 0),
                  valueToWayNum.getOrDefault(values[i] + 1, 0)));

      valueToWayNum.put(values[i], addMod(valueToWayNum.getOrDefault(values[i], 0), result[i]));
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
  }
}