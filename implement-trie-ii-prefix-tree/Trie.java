import java.util.HashMap;
import java.util.Map;

class Trie {
  Node root = new Node();

  public void insert(String word) {
    Node node = root;
    for (char letter : word.toCharArray()) {
      node.letterToChild.putIfAbsent(letter, new Node());
      node = node.letterToChild.get(letter);

      ++node.count;
    }

    node.letterToChild.putIfAbsent(null, new Node());
    ++node.letterToChild.get(null).count;
  }

  public int countWordsEqualTo(String word) {
    Node node = root;
    for (char letter : word.toCharArray()) {
      if (!node.letterToChild.containsKey(letter)) {
        return 0;
      }

      node = node.letterToChild.get(letter);
    }

    return node.letterToChild.getOrDefault(null, new Node()).count;
  }

  public int countWordsStartingWith(String prefix) {
    Node node = root;
    for (char letter : prefix.toCharArray()) {
      if (!node.letterToChild.containsKey(letter)) {
        return 0;
      }

      node = node.letterToChild.get(letter);
    }

    return node.count;
  }

  public void erase(String word) {
    Node node = root;
    for (char letter : word.toCharArray()) {
      node = node.letterToChild.get(letter);
      --node.count;
    }

    --node.letterToChild.get(null).count;
  }
}

class Node {
  Map<Character, Node> letterToChild = new HashMap<>();
  int count;
}

// Your Trie object will be instantiated and called as such:
// Trie obj = new Trie();
// obj.insert(word);
// int param_2 = obj.countWordsEqualTo(word);
// int param_3 = obj.countWordsStartingWith(prefix);
// obj.erase(word);
