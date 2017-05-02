import java.util.HashMap;
import java.util.Map;

public class PermutationInString {
	public boolean checkInclusion(String s1, String s2) {
		if (s2.length() < s1.length()) {
			return false;
		}

		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		s1.chars().forEach(ch -> updateLetter2count(letter2count, (char) ch, -1));

		for (int i = 0; i < s1.length() - 1; i++) {
			updateLetter2count(letter2count, s2.charAt(i), 1);
		}

		for (int i = s1.length() - 1; i < s2.length(); i++) {
			updateLetter2count(letter2count, s2.charAt(i), 1);

			if (letter2count.isEmpty()) {
				return true;
			}

			updateLetter2count(letter2count, s2.charAt(i - s1.length() + 1), -1);
		}
		return false;
	}

	void updateLetter2count(Map<Character, Integer> letter2count, char letter, int delta) {
		letter2count.put(letter, letter2count.getOrDefault(letter, 0) + delta);
		if (letter2count.get(letter) == 0) {
			letter2count.remove(letter);
		}
	}
}
