import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public List<String> findAndReplacePattern(String[] words, String pattern) {
		String patternSign = computeSign(pattern);

		return Arrays.stream(words).filter(word -> computeSign(word).equals(patternSign)).collect(Collectors.toList());
	}

	String computeSign(String s) {
		StringBuilder sign = new StringBuilder();
		Map<Character, Character> letterToReplacement = new HashMap<>();
		for (char letter : s.toCharArray()) {
			if (!letterToReplacement.containsKey(letter)) {
				letterToReplacement.put(letter, (char) ('a' + letterToReplacement.size()));
			}

			sign.append(letterToReplacement.get(letter));
		}
		return sign.toString();
	}
}
