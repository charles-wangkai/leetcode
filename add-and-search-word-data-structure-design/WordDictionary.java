import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class WordDictionary {
  TrieNode root = new TrieNode();

  public void addWord(String word) {
    TrieNode node = root;
    for (char letter : word.toCharArray()) {
      node = node.putChild(letter);
    }
    node.putChild(null);
  }

  public boolean search(String word) {
    return search(word, 0, root);
  }

  private boolean search(String word, int index, TrieNode node) {
    if (index == word.length()) {
      return node.hasChild(null);
    }

    char ch = word.charAt(index);

    return (ch == '.')
        ? node.getChildren().stream().anyMatch(child -> search(word, index + 1, child))
        : (node.hasChild(ch) && search(word, index + 1, node.getChild(ch)));
  }
}

class TrieNode {
  private Map<Character, TrieNode> letterToChild = new HashMap<>();

  public boolean hasChild(Character ch) {
    return letterToChild.containsKey(ch);
  }

  public TrieNode getChild(Character ch) {
    return letterToChild.get(ch);
  }

  public TrieNode putChild(Character ch) {
    letterToChild.putIfAbsent(ch, new TrieNode());

    return getChild(ch);
  }

  public List<TrieNode> getChildren() {
    return letterToChild.entrySet().stream()
        .filter(entry -> entry.getKey() != null)
        .map(Entry::getValue)
        .collect(Collectors.toList());
  }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary obj = new WordDictionary();
// obj.addWord(word);
// boolean param_2 = obj.search(word);
