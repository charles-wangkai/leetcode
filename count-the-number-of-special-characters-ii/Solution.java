import java.util.HashMap;
import java.util.Map;

class Solution {
  public int numberOfSpecialChars(String word) {
    Map<Character, Integer> lowerToLastIndex = new HashMap<>();
    Map<Character, Integer> upperToFirstIndex = new HashMap<>();
    for (int i = 0; i < word.length(); ++i) {
      char letter = word.charAt(i);
      if (Character.isLowerCase(letter)) {
        lowerToLastIndex.put(letter, i);
      } else if (!upperToFirstIndex.containsKey(letter)) {
        upperToFirstIndex.put(letter, i);
      }
    }

    return (int)
        lowerToLastIndex.keySet().stream()
            .filter(
                lower ->
                    lowerToLastIndex.get(lower)
                        < upperToFirstIndex.getOrDefault(Character.toUpperCase(lower), -1))
            .count();
  }
}