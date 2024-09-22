import java.util.HashMap;
import java.util.Map;

class Solution {
  public long validSubstringCount(String word1, String word2) {
    Map<Character, Integer> letterToCount2 = new HashMap<>();
    for (char letter : word2.toCharArray()) {
      updateMap(letterToCount2, letter, 1);
    }

    long result = 0;
    Map<Character, Integer> letterToCount1 = new HashMap<>();
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < word1.length(); ++beginIndex) {
      while (endIndex != word1.length() - 1 && !isValid(letterToCount1, letterToCount2)) {
        ++endIndex;
        updateMap(letterToCount1, word1.charAt(endIndex), 1);
      }

      if (isValid(letterToCount1, letterToCount2)) {
        result += word1.length() - endIndex;
      }

      updateMap(letterToCount1, word1.charAt(beginIndex), -1);
    }

    return result;
  }

  boolean isValid(Map<Character, Integer> letterToCount1, Map<Character, Integer> letterToCount2) {
    return letterToCount2.keySet().stream()
        .allMatch(letter -> letterToCount1.getOrDefault(letter, 0) >= letterToCount2.get(letter));
  }

  void updateMap(Map<Character, Integer> letterToCount, char letter, int delta) {
    letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
    letterToCount.remove(letter, 0);
  }
}