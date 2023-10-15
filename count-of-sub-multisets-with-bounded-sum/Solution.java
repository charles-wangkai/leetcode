// https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/solutions/4168129/c-python-knapsack-dp/

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countSubMultisets(List<Integer> nums, int l, int r) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int[] dp = new int[nums.stream().mapToInt(Integer::intValue).sum() + 1];
    dp[0] = 1;
    for (int value : valueToCount.keySet()) {
      if (value != 0) {
        for (int remainder = 0; remainder < value; ++remainder) {
          int lastIndex = (dp.length - 1 + (value - 1)) / value * value + remainder;
          while (lastIndex >= dp.length) {
            lastIndex -= value;
          }

          int prevSum = 0;
          int rightIndex = lastIndex - value;
          int leftIndex = lastIndex;
          for (int i = 0; i < valueToCount.get(value); ++i) {
            leftIndex -= value;
            if (leftIndex >= 0) {
              prevSum = addMod(prevSum, dp[leftIndex]);
            }
          }
          for (int i = lastIndex; i >= 0; i -= value) {
            dp[i] = addMod(dp[i], prevSum);

            if (rightIndex >= 0) {
              prevSum = addMod(prevSum, -dp[rightIndex]);
            }
            rightIndex -= value;

            leftIndex -= value;
            if (leftIndex >= 0) {
              prevSum = addMod(prevSum, dp[leftIndex]);
            }
          }
        }
      }
    }

    return multiplyMod(
        IntStream.rangeClosed(l, r)
            .map(i -> (i < dp.length) ? dp[i] : 0)
            .reduce(this::addMod)
            .getAsInt(),
        valueToCount.getOrDefault(0, 0) + 1);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
