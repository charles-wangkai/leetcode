import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String replaceWords(List<String> dictionary, String sentence) {
    Node trie = new Node();
    for (String word : dictionary) {
      addTrie(trie, word);
    }

    return Arrays.stream(sentence.split(" "))
        .map(word -> replaceWord(trie, word))
        .collect(Collectors.joining(" "));
  }

  void addTrie(Node trie, String word) {
    for (int i = 0; i < word.length(); ++i) {
      char letter = word.charAt(i);
      trie.letterToChild.putIfAbsent(letter, new Node());
      trie = trie.letterToChild.get(letter);
    }
    trie.letterToChild.put(null, null);
  }

  String replaceWord(Node trie, String word) {
    for (int i = 0; i < word.length(); ++i) {
      char letter = word.charAt(i);
      if (!trie.letterToChild.containsKey(letter)) {
        return word;
      }

      trie = trie.letterToChild.get(letter);
      if (trie.letterToChild.containsKey(null)) {
        return word.substring(0, i + 1);
      }
    }

    return word;
  }
}

class Node {
  Map<Character, Node> letterToChild = new HashMap<>();
}
