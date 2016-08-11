import java.util.HashMap;
import java.util.Map;

public class RansomNote {
	public boolean canConstruct(String ransomNote, String magazine) {
		Map<Character, Integer> ransomLetter2count = buildLetter2count(ransomNote);
		Map<Character, Integer> magazineLetter2count = buildLetter2count(magazine);

		for (char ransomLetter : ransomLetter2count.keySet()) {
			if (!magazineLetter2count.containsKey(ransomLetter)
					|| magazineLetter2count.get(ransomLetter) < ransomLetter2count.get(ransomLetter)) {
				return false;
			}
		}
		return true;
	}

	Map<Character, Integer> buildLetter2count(String s) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (char letter : s.toCharArray()) {
			if (!letter2count.containsKey(letter)) {
				letter2count.put(letter, 0);
			}
			letter2count.put(letter, letter2count.get(letter) + 1);
		}
		return letter2count;
	}
}
