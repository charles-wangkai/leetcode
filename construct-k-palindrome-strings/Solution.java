import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean canConstruct(String s, int k) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    int oddCount = (int) letterToCount.values().stream().filter(count -> count % 2 == 1).count();

    return k >= oddCount && k <= s.length();
  }
}