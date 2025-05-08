// https://leetcode.com/problems/find-sum-of-array-product-of-magical-sequences/solutions/6712520/bitmask-dp-by-harkness-1p3j/

import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int magicalSum(int m, int k, int[] nums) {
    int[] factorials = buildFactorials(m);
    int[] factorialInvs =
        Arrays.stream(factorials)
            .map(x -> BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue())
            .toArray();

    return computeProductSum(
        new Integer[2 * m + 1][m + 1][k + 1][nums.length],
        nums,
        factorials,
        factorialInvs,
        0,
        m,
        k,
        0);
  }

  int computeProductSum(
      Integer[][][][] cache,
      int[] nums,
      int[] factorials,
      int[] factorialInvs,
      int mask,
      int restSize,
      int restBitNum,
      int index) {
    if (restSize == 0) {
      return (Integer.bitCount(mask) == restBitNum) ? 1 : 0;
    }
    if (index == nums.length || restBitNum == -1) {
      return 0;
    }

    if (cache[mask][restSize][restBitNum][index] == null) {
      int result = 0;
      int power = 1;
      for (int i = 0; i <= restSize; ++i) {
        result =
            addMod(
                result,
                multiplyMod(
                    computeProductSum(
                        cache,
                        nums,
                        factorials,
                        factorialInvs,
                        (mask + i) >> 1,
                        restSize - i,
                        restBitNum - ((mask + i) & 1),
                        index + 1),
                    multiplyMod(power, CMod(factorials, factorialInvs, restSize, i))));

        power = multiplyMod(power, nums[index]);
      }

      cache[mask][restSize][restBitNum][index] = result;
    }

    return cache[mask][restSize][restBitNum][index];
  }

  int[] buildFactorials(int m) {
    int[] result = new int[m + 1];
    result[0] = 1;
    for (int i = 1; i < result.length; ++i) {
      result[i] = multiplyMod(result[i - 1], i);
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int CMod(int[] factorials, int[] factorialInvs, int n, int r) {
    return multiplyMod(factorials[n], multiplyMod(factorialInvs[r], factorialInvs[n - r]));
  }
}
