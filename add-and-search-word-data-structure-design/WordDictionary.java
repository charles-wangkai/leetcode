import java.util.HashMap;
import java.util.Map;

class WordDictionary {
  TrieNode root = new TrieNode();

  public void addWord(String word) {
    TrieNode node = root;
    for (char letter : word.toCharArray()) {
      node.letterToChild.putIfAbsent(letter, new TrieNode());
      node = node.letterToChild.get(letter);
    }
    node.letterToChild.putIfAbsent(null, null);
  }

  public boolean search(String word) {
    return search(word, 0, root);
  }

  private boolean search(String word, int index, TrieNode node) {
    if (index == word.length()) {
      return node.letterToChild.containsKey(null);
    }

    char letter = word.charAt(index);
    if (letter != '.') {
      return node.letterToChild.containsKey(letter)
          && search(word, index + 1, node.letterToChild.get(letter));
    }

    for (Character c : node.letterToChild.keySet()) {
      if (c != null && search(word, index + 1, node.letterToChild.get(c))) {
        return true;
      }
    }

    return false;
  }
}

class TrieNode {
  Map<Character, TrieNode> letterToChild = new HashMap<>();
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary obj = new WordDictionary();
// obj.addWord(word);
// boolean param_2 = obj.search(word);
