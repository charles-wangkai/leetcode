import java.util.HashMap;
import java.util.Map;

public class Solution {
	public String shortestCompletingWord(String licensePlate, String[] words) {
		Map<Character, Integer> targetLetter2count = computeLetter2count(licensePlate);

		String result = null;
		for (String word : words) {
			Map<Character, Integer> letter2count = computeLetter2count(word);
			if (targetLetter2count.keySet().stream()
					.allMatch(targetLetter -> letter2count.containsKey(targetLetter)
							&& letter2count.get(targetLetter) >= targetLetter2count.get(targetLetter))
					&& (result == null || word.length() < result.length())) {
				result = word;
			}
		}
		return result;
	}

	Map<Character, Integer> computeLetter2count(String s) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (char ch : s.toLowerCase().toCharArray()) {
			if (Character.isLetter(ch)) {
				letter2count.put(ch, letter2count.getOrDefault(ch, 0) + 1);
			}
		}
		return letter2count;
	}
}
