import java.util.HashMap;
import java.util.Map;

class Trie {
  private TrieNode root = new TrieNode();

  public void insert(String word) {
    TrieNode node = root;
    for (char letter : word.toCharArray()) {
      node = node.putChild(letter);
    }

    node.putChild(null);
  }

  public boolean search(String word) {
    TrieNode node = root;
    for (char letter : word.toCharArray()) {
      if (!node.hasChild(letter)) {
        return false;
      }

      node = node.getChild(letter);
    }

    return node.hasChild(null);
  }

  public boolean startsWith(String prefix) {
    TrieNode node = root;
    for (char letter : prefix.toCharArray()) {
      if (!node.hasChild(letter)) {
        return false;
      }

      node = node.getChild(letter);
    }

    return true;
  }
}

class TrieNode {
  private Map<Character, TrieNode> letterToChild = new HashMap<>();

  public boolean hasChild(Character letter) {
    return letterToChild.containsKey(letter);
  }

  public TrieNode getChild(Character letter) {
    return letterToChild.get(letter);
  }

  public TrieNode putChild(Character letter) {
    if (!hasChild(letter)) {
      letterToChild.put(letter, new TrieNode());
    }

    return getChild(letter);
  }
}

// Your Trie object will be instantiated and called as such:
// Trie obj = new Trie();
// obj.insert(word);
// boolean param_2 = obj.search(word);
// boolean param_3 = obj.startsWith(prefix);
