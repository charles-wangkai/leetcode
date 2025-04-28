import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  public String findCommonResponse(List<List<String>> responses) {
    Map<String, Integer> responseToCount = new HashMap<>();
    for (List<String> res : responses) {
      for (String r : Set.copyOf(res)) {
        responseToCount.put(r, responseToCount.getOrDefault(r, 0) + 1);
      }
    }

    int maxCount = responseToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();

    return responseToCount.keySet().stream()
        .filter(response -> responseToCount.get(response) == maxCount)
        .min(Comparator.naturalOrder())
        .get();
  }
}