import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] longestCommonPrefix(String[] words, int k) {
    Node trie = new Node(0);
    for (String word : words) {
      update(k, word, 1, trie);
    }

    int[] result = new int[words.length];
    for (int i = 0; i < result.length; ++i) {
      update(k, words[i], -1, trie);
      result[i] = trie.maxLength;
      update(k, words[i], 1, trie);
    }

    return result;
  }

  void update(int k, String word, int delta, Node node) {
    node.count += delta;

    if (node.length != word.length()) {
      char letter = word.charAt(node.length);
      node.children.putIfAbsent(letter, new Node(node.length + 1));
      Node child = node.children.get(letter);
      update(k, word, delta, child);
    }

    node.maxLength = (node.count >= k) ? node.length : 0;
    for (Node ch : node.children.values()) {
      node.maxLength = Math.max(node.maxLength, ch.maxLength);
    }
  }
}

class Node {
  int length;
  int count = 0;
  int maxLength = 0;
  Map<Character, Node> children = new HashMap<>();

  Node(int length) {
    this.length = length;
  }
}