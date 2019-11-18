import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public List<String> generateSentences(List<List<String>> synonyms, String text) {
		Map<String, String> wordToParent = new HashMap<>();
		for (List<String> synonym : synonyms) {
			String word1 = synonym.get(0);
			String word2 = synonym.get(1);

			String root1 = findRoot(wordToParent, word1);
			String root2 = findRoot(wordToParent, word2);
			if (!root1.equals(root2)) {
				wordToParent.put(root2, root1);
			}
		}

		Map<String, List<String>> rootToGroup = new HashMap<>();
		for (String word : wordToParent.keySet()) {
			String root = findRoot(wordToParent, word);
			if (!rootToGroup.containsKey(root)) {
				rootToGroup.put(root, new ArrayList<>());
			}
			rootToGroup.get(root).add(word);
		}

		List<String> sentences = new ArrayList<>();
		search(sentences, wordToParent, rootToGroup, text.split(" "), new ArrayList<String>());

		Collections.sort(sentences);

		return sentences;
	}

	String findRoot(Map<String, String> wordToParent, String word) {
		String root = word;
		while (wordToParent.containsKey(root)) {
			root = wordToParent.get(root);
		}

		String p = word;
		while (!p.equals(root)) {
			String next = wordToParent.get(p);
			wordToParent.put(p, root);

			p = next;
		}

		return root;
	}

	void search(List<String> sentences, Map<String, String> wordToParent, Map<String, List<String>> rootToGroup,
			String[] words, List<String> translated) {
		if (translated.size() == words.length) {
			sentences.add(String.join(" ", translated));

			return;
		}

		String word = words[translated.size()];
		List<String> ws = new ArrayList<>();
		String root = findRoot(wordToParent, word);
		ws.add(root);
		ws.addAll(rootToGroup.getOrDefault(root, Collections.emptyList()));

		for (String w : ws) {
			translated.add(w);
			search(sentences, wordToParent, rootToGroup, words, translated);
			translated.remove(translated.size() - 1);
		}
	}
}
