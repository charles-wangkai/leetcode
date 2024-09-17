import java.util.HashMap;
import java.util.Map;

class Solution {
  public String[] uncommonFromSentences(String s1, String s2) {
    Map<String, Integer> wordToCount = new HashMap<>();
    for (String word : (s1 + " " + s2).split(" ")) {
      wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
    }

    return wordToCount.keySet().stream()
        .filter(word -> wordToCount.get(word) == 1)
        .toArray(String[]::new);
  }
}
