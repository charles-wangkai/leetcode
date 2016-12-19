import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Arrays.sort(words, (word1, word2) -> word1.length() - word2.length());

		List<String> result = new ArrayList<String>();
		Set<String> preWords = new HashSet<String>();
		for (int i = 0; i < words.length; i++) {
			if (canForm(words[i], preWords)) {
				result.add(words[i]);
			}

			preWords.add(words[i]);
		}
		return result;
	}

	boolean canForm(String s, Set<String> dict) {
		if (dict.isEmpty()) {
			return false;
		}

		boolean[] separables = new boolean[s.length() + 1];
		separables[0] = true;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (separables[j] && dict.contains(s.substring(j, i + 1))) {
					separables[i + 1] = true;
					break;
				}
			}
		}
		return separables[s.length()];
	}
}
