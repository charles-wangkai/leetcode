import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public long minCost(String s, int[] cost) {
    Map<Character, Long> letterToCost = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      letterToCost.put(letter, letterToCost.getOrDefault(letter, 0L) + cost[i]);
    }

    return Arrays.stream(cost).asLongStream().sum()
        - letterToCost.values().stream().mapToLong(Long::longValue).max().getAsLong();
  }
}