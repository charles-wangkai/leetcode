import java.util.HashMap;
import java.util.Map;

class Solution {
  public long validSubstringCount(String word1, String word2) {
    Map<Character, Integer> letterToCount2 = new HashMap<>();
    for (char letter : word2.toCharArray()) {
      updateMap(letterToCount2, letter, 1);
    }

    long result = 0;
    int rest = letterToCount2.size();
    Map<Character, Integer> letterToCount1 = new HashMap<>();
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < word1.length(); ++beginIndex) {
      while (endIndex != word1.length() - 1 && rest != 0) {
        ++endIndex;

        char letter = word1.charAt(endIndex);
        updateMap(letterToCount1, letter, 1);
        if (letterToCount1.get(letter).intValue() == letterToCount2.getOrDefault(letter, 0)) {
          --rest;
        }
      }

      if (rest == 0) {
        result += word1.length() - endIndex;
      }

      char letter = word1.charAt(beginIndex);
      if (letterToCount1.get(letter).intValue() == letterToCount2.getOrDefault(letter, 0)) {
        ++rest;
      }
      updateMap(letterToCount1, letter, -1);
    }

    return result;
  }

  void updateMap(Map<Character, Integer> letterToCount, char letter, int delta) {
    letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
    letterToCount.remove(letter, 0);
  }
}