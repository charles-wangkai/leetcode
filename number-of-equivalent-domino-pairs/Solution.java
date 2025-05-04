import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int numEquivDominoPairs(int[][] dominoes) {
    Map<String, Integer> keyToCount = new HashMap<>();
    for (int[] domino : dominoes) {
      String key = generateKey(domino);
      keyToCount.put(key, keyToCount.getOrDefault(key, 0) + 1);
    }

    return keyToCount.values().stream().mapToInt(count -> count * (count - 1) / 2).sum();
  }

  String generateKey(int[] domino) {
    return Arrays.toString(Arrays.stream(domino).sorted().toArray());
  }
}
