import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class SimilarStringGroups {
	public int numSimilarGroups(String[] A) {
		Map<String, List<String>> wordToSimilarWords = new HashMap<>();
		for (String word : A) {
			wordToSimilarWords.put(word, new ArrayList<>());
		}

		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (isSimilar(A[i], A[j])) {
					wordToSimilarWords.get(A[i]).add(A[j]);
					wordToSimilarWords.get(A[j]).add(A[i]);
				}
			}
		}

		int groupNum = 0;
		Set<String> visited = new HashSet<>();
		for (String word : A) {
			if (!visited.contains(word)) {
				search(wordToSimilarWords, visited, word);

				groupNum++;
			}
		}
		return groupNum;
	}

	boolean isSimilar(String word1, String word2) {
		int[] diffIndexes = IntStream.range(0, word1.length()).filter(i -> word1.charAt(i) != word2.charAt(i))
				.toArray();

		return diffIndexes.length == 2 && word2.charAt(diffIndexes[0]) == word1.charAt(diffIndexes[1])
				&& word2.charAt(diffIndexes[1]) == word1.charAt(diffIndexes[0]);
	}

	void search(Map<String, List<String>> wordToSimilarWords, Set<String> visited, String word) {
		visited.add(word);

		for (String similarWord : wordToSimilarWords.get(word)) {
			if (!visited.contains(similarWord)) {
				search(wordToSimilarWords, visited, similarWord);
			}
		}
	}
}
