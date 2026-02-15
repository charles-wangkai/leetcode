import java.util.HashMap;
import java.util.Map;

class Solution {
  public int prefixConnected(String[] words, int k) {
    Map<String, Integer> prefixToCount = new HashMap<>();
    for (String word : words) {
      if (word.length() >= k) {
        String prefix = word.substring(0, k);
        prefixToCount.put(prefix, prefixToCount.getOrDefault(prefix, 0) + 1);
      }
    }

    return (int) prefixToCount.values().stream().filter(count -> count != 1).count();
  }
}