import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	public List<TrieNode> getChildrenNodes() {
		List<TrieNode> childrenNodes = new ArrayList<TrieNode>();
		for (Entry<Character, TrieNode> entry : children.entrySet()) {
			if (entry.getKey() != null) {
				childrenNodes.add(entry.getValue());
			}
		}
		return childrenNodes;
	}
}

public class WordDictionary {
	TrieNode root = new TrieNode();

	// Adds a word into the data structure.
	public void addWord(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			node = node.putChild(word.charAt(i));
		}
		node.putChild(null);
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return search(word, 0, root);
	}

	private boolean search(String word, int index, TrieNode node) {
		if (index == word.length()) {
			return node.hasChild(null);
		}

		char ch = word.charAt(index);
		if (ch == '.') {
			for (TrieNode childNode : node.getChildrenNodes()) {
				if (search(word, index + 1, childNode)) {
					return true;
				}
			}
			return false;
		} else {
			if (!node.hasChild(ch)) {
				return false;
			}
			return search(word, index + 1, node.getChild(ch));
		}
	}
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");