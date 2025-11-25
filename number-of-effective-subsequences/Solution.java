// https://leetcode.com/problems/number-of-effective-subsequences/solutions/7368610/inclusion-exclusion-principle-sos-dp-bit-i5bv/
// https://usaco.guide/plat/dp-sos?lang=cpp#optimized-memory-usage

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countEffective(int[] nums) {
    int[] twoPowers = new int[nums.length + 1];
    twoPowers[0] = 1;
    for (int i = 1; i < twoPowers.length; ++i) {
      twoPowers[i] = multiplyMod(twoPowers[i - 1], 2);
    }

    int totalOr = Arrays.stream(nums).reduce((acc, x) -> acc | x).getAsInt();
    int bitNum = Integer.toBinaryString(totalOr).length();

    int[] dp = new int[1 << bitNum];
    for (int num : nums) {
      ++dp[num];
    }
    buildSumOverSubsetsDp(dp, bitNum);

    int coverAllNum =
        IntStream.rangeClosed(0, totalOr)
            .filter(mask -> (mask & totalOr) == mask)
            .map(
                mask ->
                    ((Integer.bitCount(totalOr ^ mask) % 2 == 0) ? 1 : -1) * twoPowers[dp[mask]])
            .reduce(this::addMod)
            .getAsInt();

    return addMod(twoPowers[nums.length], -coverAllNum);
  }

  void buildSumOverSubsetsDp(int[] dp, int bitNum) {
    for (int b = 0; b < bitNum; ++b) {
      for (int i = 0; i < dp.length; ++i) {
        if (((i >> b) & 1) == 1) {
          dp[i] += dp[i ^ (1 << b)];
        }
      }
    }
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
