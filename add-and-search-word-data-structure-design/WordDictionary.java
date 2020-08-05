import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class TrieNode {
	// Initialize your data structure here.
	private Map<Character, TrieNode> letterToChild;

	public TrieNode() {
		letterToChild = new HashMap<>();
	}

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
		return letterToChild.entrySet().stream().filter(entry -> entry.getKey() != null).map(Entry::getValue)
				.collect(Collectors.toList());
	}
}

class WordDictionary {
	TrieNode root = new TrieNode();

	// Adds a word into the data structure.
	public void addWord(String word) {
		TrieNode node = root;
		for (char letter : word.toCharArray()) {
			node = node.putChild(letter);
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
			return node.getChildren().stream().anyMatch(child -> search(word, index + 1, child));
		} else {
			return node.hasChild(ch) && search(word, index + 1, node.getChild(ch));
		}
	}
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");