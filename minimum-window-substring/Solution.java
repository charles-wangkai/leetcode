import java.util.HashMap;
import java.util.Map;

class Solution {
  public String minWindow(String s, String t) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : t.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    int minLength = Integer.MAX_VALUE;
    int startForMinLength = -1;
    int beginIndex = 0;
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      if (letterToCount.containsKey(letter)) {
        letterToCount.put(letter, letterToCount.get(letter) - 1);
        while (letterToCount.values().stream().allMatch(count -> count <= 0)) {
          if (i - beginIndex + 1 < minLength) {
            minLength = i - beginIndex + 1;
            startForMinLength = beginIndex;
          }

          char beginLetter = s.charAt(beginIndex);
          if (letterToCount.containsKey(beginLetter)) {
            letterToCount.put(beginLetter, letterToCount.get(beginLetter) + 1);
          }
          ++beginIndex;
        }
      }
    }

    return (minLength == Integer.MAX_VALUE)
        ? ""
        : s.substring(startForMinLength, startForMinLength + minLength);
  }
}
