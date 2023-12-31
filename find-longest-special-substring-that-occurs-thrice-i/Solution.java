import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumLength(String s) {
    for (int length = s.length(); length >= 1; --length) {
      Map<String, Integer> strToCount = new HashMap<>();
      for (int i = 0; i + length <= s.length(); ++i) {
        String str = s.substring(i, i + length);
        if (str.chars().distinct().count() == 1) {
          strToCount.put(str, strToCount.getOrDefault(str, 0) + 1);
        }
      }

      if (strToCount.values().stream().anyMatch(count -> count >= 3)) {
        return length;
      }
    }

    return -1;
  }
}