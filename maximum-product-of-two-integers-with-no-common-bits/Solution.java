// https://leetcode.com/problems/maximum-product-of-two-integers-with-no-common-bits/solutions/7140200/bitmask-subset-dp/

import java.util.Arrays;

class Solution {
  public long maxProduct(int[] nums) {
    int bitNum = computeExponent(Arrays.stream(nums).max().getAsInt()) + 1;
    int[] dp = new int[1 << bitNum];
    for (int num : nums) {
      dp[num] = num;
    }

    for (int mask = 0; mask < 1 << bitNum; ++mask) {
      for (int b = 0; b < bitNum; ++b) {
        if (((mask >> b) & 1) == 1) {
          dp[mask] = Math.max(dp[mask], dp[mask - (1 << b)]);
        }
      }
    }

    return Arrays.stream(nums)
        .mapToLong(x -> (long) x * dp[((1 << bitNum) - 1) ^ x])
        .max()
        .getAsLong();
  }

  int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }
}
