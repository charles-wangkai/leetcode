import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
	public List<Integer> findSubstring(String S, String[] L) {
		List<Integer> result = new ArrayList<Integer>();
		if (L.length > 0) {
			Map<String, Integer> word2count = new HashMap<String, Integer>();
			for (String word : L) {
				if (!word2count.containsKey(word)) {
					word2count.put(word, 0);
				}
				word2count.put(word, word2count.get(word) + 1);
			}

			for (int i = 0; i < L[0].length(); i++) {
				search(result, word2count, S, L, i);
			}
		}
		return result;
	}

	void search(List<Integer> result, Map<String, Integer> word2count,
			String S, String[] L, int startIndex) {
		int wordLen = L[0].length();
		int backIndex = -1;
		int frontIndex;
		for (frontIndex = startIndex; frontIndex + wordLen <= S.length(); frontIndex += wordLen) {
			String word = S.substring(frontIndex, frontIndex + wordLen);
			if (!word2count.containsKey(word)) {
				restoreWord2count(backIndex, frontIndex, wordLen, word2count, S);
				backIndex = -1;
				continue;
			}

			while (word2count.get(word) == 0) {
				backIndex = moveBackIndex(backIndex, wordLen, word2count, S);
			}
			word2count.put(word, word2count.get(word) - 1);
			if (backIndex < 0) {
				backIndex = frontIndex;
			}

			if (frontIndex - backIndex == wordLen * (L.length - 1)) {
				result.add(backIndex);
			}
		}

		restoreWord2count(backIndex, frontIndex, wordLen, word2count, S);
	}

	void restoreWord2count(int backIndex, int frontIndex, int wordLen,
			Map<String, Integer> word2count, String S) {
		if (backIndex >= 0) {
			while (backIndex <= frontIndex - wordLen) {
				backIndex = moveBackIndex(backIndex, wordLen, word2count, S);
			}
		}
	}

	int moveBackIndex(int backIndex, int wordLen,
			Map<String, Integer> word2count, String S) {
		String w = S.substring(backIndex, backIndex + wordLen);
		word2count.put(w, word2count.get(w) + 1);
		return backIndex + wordLen;
	}
}
