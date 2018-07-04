import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public String longestWord(String[] words) {
		Set<String> wordSet = Arrays.stream(words).collect(Collectors.toSet());

		String result = "";
		for (String word : words) {
			if (isValid(wordSet, word) && (word.length() > result.length()
					|| (word.length() == result.length() && word.compareTo(result) < 0))) {
				result = word;
			}
		}
		return result;
	}

	boolean isValid(Set<String> wordSet, String word) {
		return IntStream.range(1, word.length()).allMatch(length -> wordSet.contains(word.substring(0, length)));
	}
}
