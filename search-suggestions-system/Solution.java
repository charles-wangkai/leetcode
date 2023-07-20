import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  static final int LIMIT = 3;

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Node trie = new Node();
    for (String product : products) {
      addToTrie(trie, product, 0);
    }

    List<List<String>> suggestionLists = new ArrayList<>();
    for (int i = 0; i < searchWord.length(); ++i) {
      suggestionLists.add(new ArrayList<>());
    }
    search(suggestionLists, trie, searchWord, 0);

    return suggestionLists;
  }

  void addToTrie(Node node, String word, int index) {
    if (index == word.length()) {
      node.ending = true;

      return;
    }

    char letter = word.charAt(index);
    if (!node.letterToChild.containsKey(letter)) {
      node.letterToChild.put(letter, new Node());
    }

    addToTrie(node.letterToChild.get(letter), word, index + 1);
  }

  void search(List<List<String>> suggestionLists, Node node, String searchWord, int index) {
    if (index == searchWord.length()) {
      return;
    }

    char letter = searchWord.charAt(index);
    if (!node.letterToChild.containsKey(letter)) {
      return;
    }
    Node child = node.letterToChild.get(letter);

    traverse(
        suggestionLists.get(index), child, new StringBuilder(searchWord.substring(0, index + 1)));

    search(suggestionLists, child, searchWord, index + 1);
  }

  void traverse(List<String> suggestionList, Node node, StringBuilder s) {
    if (node.ending) {
      suggestionList.add(s.toString());
      if (suggestionList.size() == LIMIT) {
        return;
      }
    }

    for (char letter : node.letterToChild.keySet()) {
      s.append(letter);

      traverse(suggestionList, node.letterToChild.get(letter), s);
      if (suggestionList.size() == LIMIT) {
        return;
      }

      s.deleteCharAt(s.length() - 1);
    }
  }
}

class Node {
  boolean ending;
  SortedMap<Character, Node> letterToChild = new TreeMap<>();
}
