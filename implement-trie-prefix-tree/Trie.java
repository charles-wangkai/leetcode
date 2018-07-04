import java.util.HashMap;
import java.util.Map;

class TrieNode {
	// Initialize your data structure here.
	private Map<Character, TrieNode> children;

	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
	}

	public boolean hasChild(Character ch) {
		return children.containsKey(ch);
	}

	public TrieNode getChild(Character ch) {
		return children.get(ch);
	}

	public TrieNode putChild(Character ch) {
		if (!hasChild(ch)) {
			children.put(ch, new TrieNode());
		}
		return getChild(ch);
	}
}

public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			node = node.putChild(word.charAt(i));
		}
		node.putChild(null);
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!node.hasChild(ch)) {
				return false;
			}
			node = node.getChild(ch);
		}
		return node.hasChild(null);
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if (!node.hasChild(ch)) {
				return false;
			}
			node = node.getChild(ch);
		}
		return true;
	}
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");