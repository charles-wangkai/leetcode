import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countWords(String[] words1, String[] words2) {
    Map<String, Integer> wordToCount1 = buildWordToCount(words1);
    Map<String, Integer> wordToCount2 = buildWordToCount(words2);

    return (int)
        wordToCount1.keySet().stream()
            .filter(word -> wordToCount1.get(word) == 1 && wordToCount2.getOrDefault(word, 0) == 1)
            .count();
  }

  Map<String, Integer> buildWordToCount(String[] words) {
    Map<String, Integer> wordToCount = new HashMap<>();
    for (String word : words) {
      wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
    }

    return wordToCount;
  }
}