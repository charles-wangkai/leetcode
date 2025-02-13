import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxDifference(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount.values().stream()
            .filter(count -> count % 2 == 1)
            .mapToInt(Integer::intValue)
            .max()
            .getAsInt()
        - letterToCount.values().stream()
            .filter(count -> count % 2 == 0)
            .mapToInt(Integer::intValue)
            .min()
            .getAsInt();
  }
}