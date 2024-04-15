// https://leetcode.com/problems/minimum-sum-of-values-by-dividing-array/solutions/5019524/python3-top-down-dp/

import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int INF = 10_000_000;

  public int minimumValueSum(int[] nums, int[] andValues) {
    int result = search(nums, andValues, new HashMap<>(), 0, 0, Integer.MAX_VALUE);

    return (result == INF) ? -1 : result;
  }

  int search(
      int[] nums,
      int[] andValues,
      Map<State, Integer> cache,
      int valueNum,
      int andValueNum,
      int mask) {
    if (valueNum == nums.length && andValueNum == andValues.length) {
      return 0;
    }
    if (valueNum == nums.length || andValueNum == andValues.length) {
      return INF;
    }

    int nextMask = mask & nums[valueNum];
    if (nextMask < andValues[andValueNum]) {
      return INF;
    }

    State state = new State(valueNum, andValueNum, mask);
    if (!cache.containsKey(state)) {
      cache.put(
          state,
          (nextMask == andValues[andValueNum])
              ? Math.min(
                  search(nums, andValues, cache, valueNum + 1, andValueNum, nextMask),
                  nums[valueNum]
                      + search(
                          nums, andValues, cache, valueNum + 1, andValueNum + 1, Integer.MAX_VALUE))
              : search(nums, andValues, cache, valueNum + 1, andValueNum, nextMask));
    }

    return cache.get(state);
  }
}

record State(int valueNum, int andValueNum, int mask) {}
