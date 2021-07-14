import java.util.HashMap;
import java.util.Map;

class Solution {
  public String customSortString(String order, String str) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : str.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    StringBuilder result = new StringBuilder();
    for (char letter : order.toCharArray()) {
      for (int i = 0; i < letterToCount.getOrDefault(letter, 0); ++i) {
        result.append(letter);
      }
    }

    for (char letter : letterToCount.keySet()) {
      if (order.indexOf(letter) == -1) {
        for (int i = 0; i < letterToCount.get(letter); ++i) {
          result.append(letter);
        }
      }
    }

    return result.toString();
  }
}
