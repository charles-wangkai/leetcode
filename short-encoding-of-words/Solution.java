import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	public int minimumLengthEncoding(String[] words) {
		Arrays.sort(words, (word1, word2) -> Integer.compare(word2.length(), word1.length()));

		Set<String> suffixes = new HashSet<String>();
		int result = 0;
		for (String word : words) {
			if (!suffixes.contains(word)) {
				result += word.length() + 1;

				for (int i = 0; i < word.length(); i++) {
					suffixes.add(word.substring(i));
				}
			}
		}
		return result;
	}
}
