import java.util.HashMap;
import java.util.Map;

class Solution {
  public int numKLenSubstrNoRepeats(String s, int k) {
    if (k > s.length()) {
      return 0;
    }

    Map<Character, Integer> letterToCount = new HashMap<>();
    for (int i = 0; i < k - 1; ++i) {
      updateMap(letterToCount, s.charAt(i), 1);
    }

    int result = 0;
    for (int i = k - 1; i < s.length(); ++i) {
      updateMap(letterToCount, s.charAt(i), 1);
      if (letterToCount.size() == k) {
        ++result;
      }

      updateMap(letterToCount, s.charAt(i - k + 1), -1);
    }
    return result;
  }

  void updateMap(Map<Character, Integer> letterToCount, char letter, int delta) {
    letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
    letterToCount.remove(letter, 0);
  }
}
