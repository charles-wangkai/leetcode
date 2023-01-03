// See hints

import java.util.HashMap;
import java.util.Map;

class Solution {
  public long fixedRatio(String s, int num1, int num2) {
    long result = 0;
    Map<Long, Integer> featureToCount = new HashMap<>();
    featureToCount.put(0L, 1);
    int zeroCount = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '0') {
        ++zeroCount;
      }

      long feature = (long) zeroCount * (num1 + num2) - (i + 1L) * num1;
      result += featureToCount.getOrDefault(feature, 0);
      featureToCount.put(feature, featureToCount.getOrDefault(feature, 0) + 1);
    }

    return result;
  }
}
