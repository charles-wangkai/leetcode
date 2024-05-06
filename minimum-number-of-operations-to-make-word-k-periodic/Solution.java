import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minimumOperationsToMakeKPeriodic(String word, int k) {
    Map<String, Integer> partToCount = new HashMap<>();
    for (int i = 0; i < word.length(); i += k) {
      String part = word.substring(i, i + k);
      partToCount.put(part, partToCount.getOrDefault(part, 0) + 1);
    }

    return word.length() / k
        - partToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }
}