import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minDeletion(String s, int k) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount.values().stream()
        .sorted(Comparator.reverseOrder())
        .skip(k)
        .mapToInt(Integer::intValue)
        .sum();
  }
}