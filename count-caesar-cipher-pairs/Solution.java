import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public long countPairs(String[] words) {
    Map<String, Integer> keyToCount = new HashMap<>();
    for (String word : words) {
      String key = buildKey(word);
      keyToCount.put(key, keyToCount.getOrDefault(key, 0) + 1);
    }

    return keyToCount.values().stream().mapToLong(count -> count * (count - 1L) / 2).sum();
  }

  String buildKey(String word) {
    int offset = Math.floorMod(word.charAt(0) - 'a', 26);

    return word.chars()
        .mapToObj(c -> (char) (Math.floorMod(c - 'a' - offset, 26) + 'a'))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}