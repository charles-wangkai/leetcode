// https://leetcode.com/problems/count-beautiful-substrings-ii/solutions/4330696/java-c-python-hashmap/

import java.util.HashMap;
import java.util.Map;

class Solution {
  public long beautifulSubstrings(String s, int k) {
    int unit = computeUnit(k);

    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] diffToCountMaps = new Map[unit];
    for (int i = 0; i < diffToCountMaps.length; ++i) {
      diffToCountMaps[i] = new HashMap<>();
    }
    diffToCountMaps[0].put(0, 1);

    int vowelCount = 0;
    int consonantCount = 0;
    for (int i = 0; i < s.length(); ++i) {
      if ("aeiou".indexOf(s.charAt(i)) == -1) {
        ++consonantCount;
      } else {
        ++vowelCount;
      }

      int diff = vowelCount - consonantCount;
      diffToCountMaps[(i + 1) % unit].put(
          diff, diffToCountMaps[(i + 1) % unit].getOrDefault(diff, 0) + 1);
    }

    long result = 0;
    for (Map<Integer, Integer> diffToCountMap : diffToCountMaps) {
      for (int count : diffToCountMap.values()) {
        result += count * (count - 1L) / 2;
      }
    }

    return result;
  }

  int computeUnit(int k) {
    for (int i = 1; ; ++i) {
      if (i * i % k == 0) {
        return 2 * i;
      }
    }
  }
}