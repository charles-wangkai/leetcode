// https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations/solutions/4520834/c-java-python-clean-bitset-dp/

import java.util.HashMap;
import java.util.Map;

class Solution {
  Map<State, Integer> cache;

  public int maxPartitionsAfterOperations(String s, int k) {
    return search(new HashMap<>(), s, k, 0, 0, false);
  }

  int search(Map<State, Integer> cache, String s, int k, int index, int mask, boolean changed) {
    if (index == s.length()) {
      return (mask == 0) ? 0 : 1;
    }

    State state = new State(index, mask, changed);
    if (!cache.containsKey(state)) {
      int result =
          (Integer.bitCount(mask) != k || ((mask >> (s.charAt(index) - 'a')) & 1) == 1)
              ? search(cache, s, k, index + 1, mask | (1 << (s.charAt(index) - 'a')), changed)
              : (1 + search(cache, s, k, index + 1, 1 << (s.charAt(index) - 'a'), changed));

      if (!changed) {
        for (int b = 0; b < 26; ++b) {
          result =
              Math.max(
                  result,
                  (Integer.bitCount(mask) != k || ((mask >> b) & 1) == 1)
                      ? search(cache, s, k, index + 1, mask | (1 << b), true)
                      : (1 + search(cache, s, k, index + 1, 1 << b, true)));
        }
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

record State(int index, int mask, boolean changed) {}
