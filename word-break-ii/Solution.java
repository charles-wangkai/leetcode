import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	public List<String> wordBreak(String s, List<String> wordDict) {
		@SuppressWarnings("unchecked")
		List<String>[] lastWordLists = new List[s.length()];
		for (int i = 0; i < lastWordLists.length; ++i) {
			lastWordLists[i] = new ArrayList<>();
		}

		for (int i = 0; i < s.length(); ++i) {
			String prefix = s.substring(0, i + 1);

			for (String word : wordDict) {
				if (prefix.endsWith(word)
						&& (word.length() == prefix.length() || !lastWordLists[i - word.length()].isEmpty())) {
					lastWordLists[i].add(word);
				}
			}
		}

		List<String> sentences = new ArrayList<>();
		search(sentences, lastWordLists, s.length(), new ArrayList<>());

		return sentences;
	}

	void search(List<String> sentences, List<String>[] lastWordLists, int length, List<String> words) {
		if (length == 0) {
			List<String> sentence = new ArrayList<>(words);
			Collections.reverse(sentence);

			sentences.add(String.join(" ", sentence));

			return;
		}

		for (String word : lastWordLists[length - 1]) {
			words.add(word);
			search(sentences, lastWordLists, length - word.length(), words);
			words.remove(words.size() - 1);
		}
	}
}
