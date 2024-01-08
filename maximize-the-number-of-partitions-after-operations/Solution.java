// https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations/solutions/4520834/c-java-python-clean-bitset-dp/

import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int ALPHABET_SIZE = 26;

  Map<State, Integer> cache;

  public int maxPartitionsAfterOperations(String s, int k) {
    cache = new HashMap<>();

    return search(s, k, 0, 0, false);
  }

  int search(String s, int k, int index, int mask, boolean changed) {
    if (index == s.length()) {
      return 1;
    }

    State state = new State(index, mask, changed);
    if (!cache.containsKey(state)) {
      int maxPartitionNum;
      if (Integer.bitCount(mask) != k || ((mask >> (s.charAt(index) - 'a')) & 1) == 1) {
        maxPartitionNum = search(s, k, index + 1, mask | (1 << (s.charAt(index) - 'a')), changed);
      } else {
        maxPartitionNum = 1 + search(s, k, index + 1, 1 << (s.charAt(index) - 'a'), changed);
      }

      if (!changed) {
        for (int i = 0; i < ALPHABET_SIZE; ++i) {
          if (Integer.bitCount(mask) != k || ((mask >> i) & 1) == 1) {
            maxPartitionNum =
                Math.max(maxPartitionNum, search(s, k, index + 1, mask | (1 << i), true));
          } else {
            maxPartitionNum = Math.max(maxPartitionNum, 1 + search(s, k, index + 1, 1 << i, true));
          }
        }
      }

      cache.put(state, maxPartitionNum);
    }

    return cache.get(state);
  }
}

record State(int index, int mask, boolean changed) {}
