// https://leetcode.com/problems/subsequences-with-a-unique-middle-mode-i/solutions/6173441/python-o-n-complement-counting/

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int subsequencesWithMiddleMode(int[] nums) {
    int[] values = Arrays.stream(nums).distinct().toArray();

    Map<Integer, Integer> leftValueToCount = new HashMap<>();
    for (int value : values) {
      leftValueToCount.put(value, 0);
    }

    Map<Integer, Integer> rightValueToCount = new HashMap<>();
    for (int num : nums) {
      updateMap(rightValueToCount, num, 1);
    }

    int result = 0;
    Aggregation aggregation =
        new Aggregation(
            0,
            rightValueToCount.values().stream()
                .mapToInt(this::squareMod)
                .reduce(this::addMod)
                .getAsInt(),
            0,
            0,
            0);
    for (int i = 0; i < nums.length; ++i) {
      updateAggregation(
          aggregation, -1, leftValueToCount.get(nums[i]), rightValueToCount.get(nums[i]));
      updateMap(rightValueToCount, nums[i], -1);
      updateAggregation(
          aggregation, 1, leftValueToCount.get(nums[i]), rightValueToCount.get(nums[i]));

      int left = i;
      int right = nums.length - 1 - i;

      result =
          addMod(
              result,
              addMod(
                  multiplyMod(nC2(left), nC2(right)),
                  -multiplyMod(
                      nC2(left - leftValueToCount.get(nums[i])),
                      nC2(right - rightValueToCount.get(nums[i])))));

      // Subtract 221 and 32 cases
      int left1 = left - leftValueToCount.get(nums[i]);
      int right1 = right - rightValueToCount.get(nums[i]);
      int p1 = addMod(aggregation.p, -squareMod(leftValueToCount.get(nums[i])));
      int q1 = addMod(aggregation.q, -squareMod(rightValueToCount.get(nums[i])));
      int u1 =
          addMod(
              aggregation.u,
              -multiplyMod(leftValueToCount.get(nums[i]), rightValueToCount.get(nums[i])));
      int v1 =
          addMod(
              aggregation.v,
              -multiplyMod(
                  squareMod(leftValueToCount.get(nums[i])), rightValueToCount.get(nums[i])));
      int w1 =
          addMod(
              aggregation.w,
              -multiplyMod(
                  leftValueToCount.get(nums[i]), squareMod(rightValueToCount.get(nums[i]))));

      result =
          addMod(
              result,
              -divideByTwoMod(
                  multiplyMod(
                      addMod(p1, -left1), multiplyMod(rightValueToCount.get(nums[i]), right1))));
      result =
          addMod(
              result,
              -divideByTwoMod(
                  multiplyMod(
                      addMod(q1, -right1), multiplyMod(leftValueToCount.get(nums[i]), left1))));
      result =
          addMod(
              result,
              -multiplyMod(
                  u1,
                  addMod(
                      multiplyMod(leftValueToCount.get(nums[i]), right1),
                      multiplyMod(rightValueToCount.get(nums[i]), left1))));
      result = addMod(result, -multiplyMod(v1, -rightValueToCount.get(nums[i])));
      result = addMod(result, -multiplyMod(w1, -leftValueToCount.get(nums[i])));

      updateAggregation(
          aggregation, -1, leftValueToCount.get(nums[i]), rightValueToCount.get(nums[i]));
      updateMap(leftValueToCount, nums[i], 1);
      updateAggregation(
          aggregation, 1, leftValueToCount.get(nums[i]), rightValueToCount.get(nums[i]));
    }

    return result;
  }

  void updateAggregation(Aggregation aggregation, int sign, int leftCount, int rightCount) {
    aggregation.p = addMod(aggregation.p, multiplyMod(sign, squareMod(leftCount)));
    aggregation.q = addMod(aggregation.q, multiplyMod(sign, squareMod(rightCount)));
    aggregation.u = addMod(aggregation.u, multiplyMod(sign, multiplyMod(leftCount, rightCount)));
    aggregation.v =
        addMod(aggregation.v, multiplyMod(sign, multiplyMod(squareMod(leftCount), rightCount)));
    aggregation.w =
        addMod(aggregation.w, multiplyMod(sign, multiplyMod(leftCount, squareMod(rightCount))));
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideByTwoMod(int x) {
    return multiplyMod(x, BigInteger.valueOf(2).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int squareMod(int x) {
    return multiplyMod(x, x);
  }

  int nC2(int n) {
    return divideByTwoMod(multiplyMod(n, n - 1));
  }
}

class Aggregation {
  int p; // sum pre[x]^2
  int q; // sum suf[x]^2
  int u; // sum pre[x] suf[x]
  int v; // sum pre[x]^2 suf[x]
  int w; // sum pre[x] suf[x]^2

  Aggregation(int p, int q, int u, int v, int w) {
    this.p = p;
    this.q = q;
    this.u = u;
    this.v = v;
    this.w = w;
  }
}