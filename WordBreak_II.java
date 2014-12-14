import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreak_II {
	public List<String> wordBreak(String s, Set<String> dict) {
		@SuppressWarnings("unchecked")
		List<String>[] lastWords = new List[s.length()];
		for (int i = 0; i < lastWords.length; i++) {
			lastWords[i] = new ArrayList<String>();
		}

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				String lastWord = s.substring(j, i + 1);
				if ((j == 0 || !lastWords[j - 1].isEmpty())
						&& dict.contains(lastWord)) {
					lastWords[i].add(lastWord);
				}
			}
		}

		List<String> result = new ArrayList<String>();
		search(result, s, s.length() - 1, dict, lastWords,
				new LinkedList<String>());
		return result;
	}

	void search(List<String> result, String s, int endIndex, Set<String> dict,
			List<String>[] lastWords, LinkedList<String> words) {
		if (endIndex < 0) {
			StringBuilder sb = new StringBuilder();
			for (String word : words) {
				if (sb.length() != 0) {
					sb.append(" ");
				}
				sb.append(word);
			}
			result.add(sb.toString());
			return;
		}
		for (String lastWord : lastWords[endIndex]) {
			words.addFirst(lastWord);
			search(result, s, endIndex - lastWord.length(), dict, lastWords,
					words);
			words.removeFirst();
		}
	}
}
