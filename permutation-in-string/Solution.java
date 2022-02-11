import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) {
      return false;
    }

    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s1.toCharArray()) {
      updateLetterToCount(letterToCount, letter, -1);
    }

    for (int i = 0; i < s1.length() - 1; ++i) {
      updateLetterToCount(letterToCount, s2.charAt(i), 1);
    }

    for (int i = s1.length() - 1; i < s2.length(); ++i) {
      updateLetterToCount(letterToCount, s2.charAt(i), 1);

      if (letterToCount.isEmpty()) {
        return true;
      }

      updateLetterToCount(letterToCount, s2.charAt(i - s1.length() + 1), -1);
    }

    return false;
  }

  void updateLetterToCount(Map<Character, Integer> letterToCount, char letter, int delta) {
    letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
    letterToCount.remove(letter, 0);
  }
}
