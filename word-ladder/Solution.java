import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Map<String, List<String>> patternToWords = new HashMap<>();
    for (String word : wordList) {
      for (int i = 0; i < word.length(); ++i) {
        String pattern = buildPattern(word, i);
        patternToWords.putIfAbsent(pattern, new ArrayList<>());
        patternToWords.get(pattern).add(word);
      }
    }

    Map<String, Integer> wordToStep = new HashMap<>();
    wordToStep.put(beginWord, 1);
    Queue<String> queue = new ArrayDeque<>();
    queue.offer(beginWord);
    while (!queue.isEmpty()) {
      String head = queue.poll();
      if (head.equals(endWord)) {
        return wordToStep.get(endWord);
      }

      for (int i = 0; i < head.length(); ++i) {
        String pattern = buildPattern(head, i);
        for (String adj : patternToWords.getOrDefault(pattern, List.of())) {
          if (!wordToStep.containsKey(adj)) {
            wordToStep.put(adj, wordToStep.get(head) + 1);
            queue.offer(adj);
          }
        }
      }
    }

    return 0;
  }

  String buildPattern(String s, int index) {
    return String.format("%s?%s", s.substring(0, index), s.substring(index + 1));
  }
}
