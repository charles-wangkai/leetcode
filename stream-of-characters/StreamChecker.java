import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamChecker {
	private TrieNode trie = new TrieNode();
	private List<Character> queries = new ArrayList<>();

	public StreamChecker(String[] words) {
		for (String word : words) {
			trie.add(word);
		}
	}

	public boolean query(char letter) {
		queries.add(letter);

		TrieNode node = trie;
		for (int i = queries.size() - 1; i >= 0; i--) {
			char query = queries.get(i);

			if (!node.letterToChild.containsKey(query)) {
				return false;
			}

			node = node.letterToChild.get(query);
			if (node.letterToChild.containsKey(null)) {
				return true;
			}
		}
		return false;
	}
}

class TrieNode {
	Map<Character, TrieNode> letterToChild = new HashMap<>();

	public void add(String word) {
		add(word, word.length() - 1);
	}

	private void add(String word, int index) {
		if (index == -1) {
			letterToChild.put(null, null);

			return;
		}

		char firstLetter = word.charAt(index);
		if (!letterToChild.containsKey(firstLetter)) {
			letterToChild.put(firstLetter, new TrieNode());
		}
		letterToChild.get(firstLetter).add(word, index - 1);
	}
}

// Your StreamChecker object will be instantiated and called as such:
// StreamChecker obj = new StreamChecker(words);
// boolean param_1 = obj.query(letter);
