import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> startIndices = new ArrayList<>();
    for (int i = 0; i < words[0].length(); ++i) {
      search(startIndices, s, words, i);
    }

    return startIndices;
  }

  void search(List<Integer> startIndices, String s, String[] words, int beginIndex) {
    Map<String, Integer> wordToCount = new HashMap<>();
    for (String word : words) {
      update(wordToCount, word, 1);
    }

    int wordLength = words[0].length();
    for (int endIndex = beginIndex; endIndex + wordLength <= s.length(); endIndex += wordLength) {
      update(wordToCount, s.substring(endIndex, endIndex + wordLength), -1);

      if (wordToCount.isEmpty()) {
        startIndices.add(beginIndex);
      }

      if ((endIndex - beginIndex) / wordLength + 1 == words.length) {
        update(wordToCount, s.substring(beginIndex, beginIndex + wordLength), 1);
        beginIndex += wordLength;
      }
    }
  }

  void update(Map<String, Integer> wordToCount, String word, int delta) {
    wordToCount.put(word, wordToCount.getOrDefault(word, 0) + delta);
    wordToCount.remove(word, 0);
  }
}
