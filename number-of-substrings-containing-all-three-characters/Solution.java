import java.util.HashMap;
import java.util.Map;

class Solution {
  public int numberOfSubstrings(String s) {
    int result = 0;
    Map<Character, Integer> letterToCount = new HashMap<>();
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < s.length(); ++endIndex) {
      updateLetterToCount(letterToCount, s.charAt(endIndex), 1);

      while (letterToCount.size() == 3 && letterToCount.get(s.charAt(beginIndex)) != 1) {
        updateLetterToCount(letterToCount, s.charAt(beginIndex), -1);
        ++beginIndex;
      }

      if (letterToCount.size() == 3) {
        result += beginIndex + 1;
      }
    }

    return result;
  }

  void updateLetterToCount(Map<Character, Integer> letterToCount, char letter, int delta) {
    letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
    letterToCount.remove(letter, 0);
  }
}