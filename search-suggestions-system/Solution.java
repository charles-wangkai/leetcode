import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		Node trie = new Node();
		for (String product : products) {
			addToTrie(trie, product);
		}

		List<List<String>> suggestionLists = IntStream.range(0, searchWord.length())
				.mapToObj(i -> new ArrayList<String>()).collect(Collectors.toList());
		search(suggestionLists, trie, searchWord, 0);

		return suggestionLists;
	}

	void addToTrie(Node trie, String word) {
		addToTrie(trie, word, 0);
	}

	void addToTrie(Node node, String word, int index) {
		if (index == word.length()) {
			node.endingCount++;
		} else {
			char letter = word.charAt(index);
			if (!node.letterToChild.containsKey(letter)) {
				node.letterToChild.put(letter, new Node());
			}

			addToTrie(node.letterToChild.get(letter), word, index + 1);
		}
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

		traverse(suggestionLists.get(index), child, new StringBuilder(searchWord.substring(0, index + 1)), 3);

		search(suggestionLists, child, searchWord, index + 1);
	}

	int traverse(List<String> suggestionList, Node node, StringBuilder s, int remain) {
		for (int i = 0; i < node.endingCount && remain > 0; i++) {
			suggestionList.add(s.toString());
			remain--;
		}

		if (remain != 0) {
			for (char letter : node.letterToChild.keySet()) {
				s.append(letter);
				remain = traverse(suggestionList, node.letterToChild.get(letter), s, remain);
				s.deleteCharAt(s.length() - 1);

				if (remain == 0) {
					break;
				}
			}
		}

		return remain;
	}
}

class Node {
	int endingCount;
	SortedMap<Character, Node> letterToChild = new TreeMap<>();
}