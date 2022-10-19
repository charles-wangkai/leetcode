import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  public List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> wordToCount = new HashMap<>();
    for (String word : words) {
      wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
    }

    return wordToCount.keySet().stream()
        .sorted(
            Comparator.comparing((String word) -> wordToCount.get(word))
                .reversed()
                .thenComparing(word -> word))
        .limit(k)
        .toList();
  }
}
