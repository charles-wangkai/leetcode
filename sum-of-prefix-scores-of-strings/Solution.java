import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] sumPrefixScores(String[] words) {
    Node trie = new Node();
    for (String word : words) {
      Node node = trie;
      for (char letter : word.toCharArray()) {
        node.letterToNode.putIfAbsent(letter, new Node());
        node = node.letterToNode.get(letter);
        ++node.prefixCount;
      }
    }

    return Arrays.stream(words)
        .mapToInt(
            word -> {
              int result = 0;
              Node node = trie;
              for (char letter : word.toCharArray()) {
                node = node.letterToNode.get(letter);
                result += node.prefixCount;
              }

              return result;
            })
        .toArray();
  }
}

class Node {
  int prefixCount;
  Map<Character, Node> letterToNode = new HashMap<>();
}