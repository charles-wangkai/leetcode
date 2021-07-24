import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean areOccurrencesEqual(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount.values().stream().distinct().count() == 1;
  }
}
