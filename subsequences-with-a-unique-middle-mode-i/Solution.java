// https://leetcode.com/problems/subsequences-with-a-unique-middle-mode-i/solutions/6171179/can-you-count/

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int MOD_INV_2 =
      BigInteger.valueOf(2).modInverse(BigInteger.valueOf(MODULUS)).intValue();

  public int subsequencesWithMiddleMode(int[] nums) {
    int result = 0;
    Map<Integer, Integer> leftValueToCount = new HashMap<>();
    Map<Integer, Integer> rightValueToCount = new HashMap<>();
    for (int value : nums) {
      updateMap(rightValueToCount, value, 1);
    }
    for (int i = 0; i < nums.length; ++i) {
      updateMap(rightValueToCount, nums[i], -1);

      int leftSameCount = leftValueToCount.getOrDefault(nums[i], 0);
      int leftDiffCount = i - leftSameCount;

      int rightSameCount = rightValueToCount.getOrDefault(nums[i], 0);
      int rightDiffCount = nums.length - i - 1 - rightSameCount;

      result = addMod(result, computeWayNumForMode5(leftSameCount, rightSameCount));
      result =
          addMod(
              result,
              computeWayNumForMode4(leftSameCount, leftDiffCount, rightSameCount, rightDiffCount));
      result =
          addMod(
              result,
              computeWayNumForMode3(leftSameCount, leftDiffCount, rightSameCount, rightDiffCount));
      result =
          addMod(
              result,
              addMod(
                  computeWayNumForMode2(nums[i], leftValueToCount, rightValueToCount),
                  computeWayNumForMode2(nums[i], rightValueToCount, leftValueToCount)));

      updateMap(leftValueToCount, nums[i], 1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }

  int computeWayNumForMode2(
      int value, Map<Integer, Integer> valueToCount1, Map<Integer, Integer> valueToCount2) {
    int sum2 = 0;
    int squareSum2 = 0;
    for (int v : valueToCount2.keySet()) {
      if (v != value) {
        int count = valueToCount2.get(v);
        sum2 = addMod(sum2, count);
        squareSum2 = addMod(squareSum2, multiplyMod(count, count));
      }
    }

    int result = 0;
    for (int v : valueToCount1.keySet()) {
      if (v != value) {
        int wayNum;
        if (valueToCount2.containsKey(v)) {
          int count = valueToCount2.get(v);
          wayNum =
              addMod(
                  multiplyMod(addMod(sum2, -count), addMod(sum2, -count)),
                  -addMod(squareSum2, -multiplyMod(count, count)));
        } else {
          wayNum = addMod(multiplyMod(sum2, sum2), -squareSum2);
        }
        wayNum = multiplyMod(wayNum, MOD_INV_2);

        result = addMod(result, multiplyMod(valueToCount1.get(v), wayNum));
      }
    }
    result = multiplyMod(result, valueToCount1.getOrDefault(value, 0));

    return result;
  }

  int computeWayNumForMode3(
      int leftSameCount, int leftDiffCount, int rightSameCount, int rightDiffCount) {
    return addMod(
        addMod(
            multiplyMod(nC2(leftSameCount), nC2(rightDiffCount)),
            multiplyMod(
                multiplyMod(leftSameCount, leftDiffCount),
                multiplyMod(rightSameCount, rightDiffCount))),
        multiplyMod(nC2(leftDiffCount), nC2(rightSameCount)));
  }

  int computeWayNumForMode4(
      int leftSameCount, int leftDiffCount, int rightSameCount, int rightDiffCount) {
    return addMod(
        multiplyMod(nC2(leftSameCount), multiplyMod(rightSameCount, rightDiffCount)),
        multiplyMod(multiplyMod(leftSameCount, leftDiffCount), nC2(rightSameCount)));
  }

  int computeWayNumForMode5(int leftSameCount, int rightSameCount) {
    return multiplyMod(nC2(leftSameCount), nC2(rightSameCount));
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int nC2(int n) {
    return multiplyMod(multiplyMod(n, n - 1), MOD_INV_2);
  }
}