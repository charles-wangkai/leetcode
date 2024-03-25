import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
    Node trie = new Node(-1);
    for (int i = 0; i < wordsContainer.length; ++i) {
      insert(trie, i, wordsContainer[i]);
    }

    fillContainerIndex(trie, wordsContainer);

    return Arrays.stream(wordsQuery)
        .mapToInt(
            word -> {
              Node node = trie;
              for (int i = word.length() - 1; i >= 0; --i) {
                char letter = word.charAt(i);
                if (!node.letterToChild.containsKey(letter)) {
                  break;
                }

                node = node.letterToChild.get(letter);
              }

              return node.containerIndex;
            })
        .toArray();
  }

  void fillContainerIndex(Node node, String[] wordsContainer) {
    if (node.containerIndex == -1) {
      for (Node child : node.letterToChild.values()) {
        fillContainerIndex(child, wordsContainer);

        if (node.containerIndex == -1
            || wordsContainer[child.containerIndex].length()
                < wordsContainer[node.containerIndex].length()
            || (wordsContainer[child.containerIndex].length()
                    == wordsContainer[node.containerIndex].length()
                && child.containerIndex < node.containerIndex)) {
          node.containerIndex = child.containerIndex;
        }
      }
    }
  }

  void insert(Node trie, int containerIndex, String word) {
    Node node = trie;
    for (int i = word.length() - 1; i >= 0; --i) {
      char letter = word.charAt(i);

      if (!node.letterToChild.containsKey(letter)) {
        node.letterToChild.put(letter, new Node(-1));
      }

      node = node.letterToChild.get(letter);
    }

    if (!node.letterToChild.containsKey(null)) {
      node.letterToChild.put(null, new Node(containerIndex));
    }
  }
}

class Node {
  Map<Character, Node> letterToChild = new HashMap<>();
  int containerIndex;

  Node(int containerIndex) {
    this.containerIndex = containerIndex;
  }
}