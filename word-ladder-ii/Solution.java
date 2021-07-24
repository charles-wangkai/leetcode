import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    Set<String> words = wordList.stream().collect(Collectors.toSet());

    Queue<Element> queue = new ArrayDeque<>();
    queue.offer(new Element(beginWord, 0));

    Map<String, Integer> wordToStep = new HashMap<>();
    wordToStep.put(beginWord, 0);

    Map<String, Set<String>> wordToPrevWords = new HashMap<>();
    wordToPrevWords.put(beginWord, Set.of());

    while (!queue.isEmpty()) {
      Element head = queue.poll();

      if (head.word.equals(endWord)) {
        List<List<String>> ladders = new ArrayList<>();
        search(ladders, wordToPrevWords, endWord, new ArrayDeque<>());

        return ladders;
      }

      for (String nextWord : findNextWords(words, head.word)) {
        if (!wordToStep.containsKey(nextWord) || wordToStep.get(nextWord) == head.step + 1) {
          if (!wordToPrevWords.containsKey(nextWord)) {
            wordToStep.put(nextWord, head.step + 1);
            queue.offer(new Element(nextWord, head.step + 1));
            wordToPrevWords.put(nextWord, new HashSet<>());
          }
          wordToPrevWords.get(nextWord).add(head.word);
        }
      }
    }

    return List.of();
  }

  List<String> findNextWords(Set<String> words, String word) {
    List<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder(word);
    for (int i = 0; i < sb.length(); ++i) {
      for (char letter = 'a'; letter <= 'z'; ++letter) {
        if (letter != word.charAt(i)) {
          sb.setCharAt(i, letter);

          String nextWord = sb.toString();
          if (words.contains(nextWord)) {
            result.add(nextWord);
          }
        }
      }
      sb.setCharAt(i, word.charAt(i));
    }

    return result;
  }

  void search(
      List<List<String>> ladders,
      Map<String, Set<String>> wordToPrevWords,
      String current,
      Deque<String> ladder) {
    ladder.offerFirst(current);

    Set<String> prevWords = wordToPrevWords.get(current);
    if (prevWords.isEmpty()) {
      ladders.add(List.copyOf(ladder));
    } else {
      for (String prevWord : prevWords) {
        search(ladders, wordToPrevWords, prevWord, ladder);
      }
    }

    ladder.pollFirst();
  }
}

class Element {
  String word;
  int step;

  public Element(String word, int step) {
    this.word = word;
    this.step = step;
  }
}
