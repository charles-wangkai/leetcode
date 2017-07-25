import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceWords {
	public String replaceWords(List<String> dict, String sentence) {
		Trie trie = new Trie();
		dict.stream().forEach(word -> trie.add(word, 0));

		StringBuilder result = new StringBuilder();
		int wordIndex = 0;
		for (int i = 0; i <= sentence.length(); i++) {
			if (i == sentence.length() || sentence.charAt(i) == ' ') {
				result.append(replaceWord(trie, sentence.substring(wordIndex, i)));

				if (i < sentence.length()) {
					result.append(" ");
				}

				wordIndex = i + 1;
			}
		}
		return result.toString();
	}

	String replaceWord(Trie trie, String word) {
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);

			if (!trie.letter2children.containsKey(letter)) {
				return word;
			}

			trie = trie.letter2children.get(letter);

			if (trie.letter2children.containsKey(null)) {
				return word.substring(0, i + 1);
			}
		}
		return word;
	}
}

class Trie {
	Map<Character, Trie> letter2children = new HashMap<Character, Trie>();

	void add(String word, int index) {
		if (index == word.length()) {
			letter2children.put(null, null);
		} else {
			char letter = word.charAt(index);
			if (!letter2children.containsKey(letter)) {
				letter2children.put(letter, new Trie());
			}
			letter2children.get(letter).add(word, index + 1);
		}
	}
}