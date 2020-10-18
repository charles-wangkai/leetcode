import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxLengthBetweenEqualCharacters(String s) {
    int result = -1;
    Map<Character, Integer> letterToFirstIndex = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      if (letterToFirstIndex.containsKey(letter)) {
        result = Math.max(result, i - letterToFirstIndex.get(letter) - 1);
      } else {
        letterToFirstIndex.put(letter, i);
      }
    }

    return result;
  }
}
