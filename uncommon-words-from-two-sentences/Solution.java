import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public String[] uncommonFromSentences(String A, String B) {
		Map<String, Integer> wordToCountA = buildWordToCount(A);
		Map<String, Integer> wordToCountB = buildWordToCount(B);

		List<String> uncommons = new ArrayList<>();
		searchUncommons(uncommons, wordToCountA, wordToCountB);
		searchUncommons(uncommons, wordToCountB, wordToCountA);
		return uncommons.toArray(new String[0]);
	}

	void searchUncommons(List<String> uncommons, Map<String, Integer> wordToCountThis,
			Map<String, Integer> wordToCountOther) {
		for (String word : wordToCountThis.keySet()) {
			if (wordToCountThis.get(word) == 1 && !wordToCountOther.containsKey(word)) {
				uncommons.add(word);
			}
		}
	}

	Map<String, Integer> buildWordToCount(String sentence) {
		Map<String, Integer> wordToCount = new HashMap<>();
		for (String word : sentence.split(" ")) {
			if (!word.isEmpty()) {
				wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
			}
		}
		return wordToCount;
	}
}
