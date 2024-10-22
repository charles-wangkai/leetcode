import java.util.HashMap;
import java.util.Map;

class Solution {
  public int numberOfSubstrings(String s, int k) {
    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      Map<Character, Integer> letterToCount = new HashMap<>();
      for (int j = i; j < s.length(); ++j) {
        char letter = s.charAt(j);
        letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
        if (letterToCount.get(letter) == k) {
          result += s.length() - j;

          break;
        }
      }
    }

    return result;
  }
}