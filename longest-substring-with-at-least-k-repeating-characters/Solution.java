import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestSubstring(String s, int k) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    int result = 0;
    int beginIndex = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i == s.length() || letterToCount.get(s.charAt(i)) < k) {
        result =
            Math.max(
                result,
                (i == s.length() && beginIndex == 0)
                    ? s.length()
                    : longestSubstring(s.substring(beginIndex, i), k));
        beginIndex = i + 1;
      }
    }

    return result;
  }
}
