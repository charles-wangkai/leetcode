import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxSameLengthRuns(String s) {
    Map<Integer, Integer> lengthToCount = new HashMap<>();
    int length = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && i != 0 && s.charAt(i) == s.charAt(i - 1)) {
        ++length;
      } else {
        if (length != 0) {
          lengthToCount.put(length, lengthToCount.getOrDefault(length, 0) + 1);
        }

        length = 1;
      }
    }

    return lengthToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }
}