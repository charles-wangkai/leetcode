import java.util.HashMap;
import java.util.Map;

class Solution {
  public String minWindow(String s, String t) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : t.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    String result = null;
    int beginIndex = 0;
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      if (letterToCount.containsKey(letter)) {
        letterToCount.put(letter, letterToCount.get(letter) - 1);
        while (letterToCount.values().stream().allMatch(count -> count <= 0)) {
          if (result == null || i - beginIndex + 1 < result.length()) {
            result = s.substring(beginIndex, i + 1);
          }

          char beginLetter = s.charAt(beginIndex);
          if (letterToCount.containsKey(beginLetter)) {
            letterToCount.put(beginLetter, letterToCount.get(beginLetter) + 1);
          }
          ++beginIndex;
        }
      }
    }

    return (result == null) ? "" : result;
  }
}
