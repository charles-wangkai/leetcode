import java.util.HashMap;
import java.util.Map;

class Solution {
  public long numberOfSubstrings(String s, int k) {
    long result = s.length() * (s.length() + 1L) / 2;

    Map<Character, Integer> letterToCount = new HashMap<>();
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      while (endIndex != s.length() - 1
          && letterToCount.getOrDefault(s.charAt(endIndex + 1), 0) + 1 < k) {
        ++endIndex;
        letterToCount.put(
            s.charAt(endIndex), letterToCount.getOrDefault(s.charAt(endIndex), 0) + 1);
      }

      result -= endIndex - beginIndex + 1;

      letterToCount.put(
          s.charAt(beginIndex), letterToCount.getOrDefault(s.charAt(beginIndex), 0) - 1);
    }

    return result;
  }
}