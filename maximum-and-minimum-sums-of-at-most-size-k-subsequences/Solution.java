import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  static Map<Integer, Integer> modInvs = new HashMap<>();

  public int minMaxSums(int[] nums, int k) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length)
        .map(
            i ->
                multiplyMod(
                    nums[i],
                    addMod(computeWayNum(i, k - 1), computeWayNum(nums.length - 1 - i, k - 1))))
        .reduce(this::addMod)
        .getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int computeModInv(int x) {
    return modInvs.computeIfAbsent(
        x, key -> BigInteger.valueOf(key).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int computeWayNum(int size, int limit) {
    int result = 1;
    int c = 1;
    for (int i = 0; i < Math.min(size, limit); ++i) {
      c = multiplyMod(c, multiplyMod(size - i, computeModInv(i + 1)));
      result = addMod(result, c);
    }

    return result;
  }
}