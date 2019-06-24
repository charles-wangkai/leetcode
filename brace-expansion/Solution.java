import java.util.ArrayList;
import java.util.List;

public class Solution {
	public String[] expand(String S) {
		List<char[]> candidatesList = new ArrayList<>();
		int index = 0;
		while (index < S.length()) {
			if (S.charAt(index) == '{') {
				int endIndex = S.indexOf('}', index);
				String[] fields = S.substring(index + 1, endIndex).split(",");

				char[] candidates = new char[fields.length];
				for (int i = 0; i < candidates.length; i++) {
					candidates[i] = fields[i].charAt(0);
				}
				candidatesList.add(candidates);

				index = endIndex + 1;
			} else {
				candidatesList.add(new char[] { S.charAt(index) });

				index++;
			}
		}

		List<String> words = new ArrayList<>();
		search(words, candidatesList, 0, new StringBuilder());
		return words.stream().sorted().toArray(String[]::new);
	}

	void search(List<String> words, List<char[]> candidatesList, int index, StringBuilder word) {
		if (index == candidatesList.size()) {
			words.add(word.toString());

			return;
		}

		for (char candidate : candidatesList.get(index)) {
			word.append(candidate);

			search(words, candidatesList, index + 1, word);

			word.deleteCharAt(word.length() - 1);
		}
	}
}
