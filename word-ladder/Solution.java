import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Map<String, List<String>> patternToWords = new HashMap<>();
    for (String word :
        Stream.concat(wordList.stream(), Stream.of(beginWord)).collect(Collectors.toList())) {
      for (int i = 0; i < word.length(); ++i) {
        String pattern = String.format("%s?%s", word.substring(0, i), word.substring(i + 1));
        if (!patternToWords.containsKey(pattern)) {
          patternToWords.put(pattern, new ArrayList<>());
        }
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
        String pattern = String.format("%s?%s", head.substring(0, i), head.substring(i + 1));
        for (String adj : patternToWords.get(pattern)) {
          if (!wordToStep.containsKey(adj)) {
            wordToStep.put(adj, wordToStep.get(head) + 1);
            queue.offer(adj);
          }
        }
      }
    }

    return 0;
  }
}
