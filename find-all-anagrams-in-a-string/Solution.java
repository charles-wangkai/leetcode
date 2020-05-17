import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> indices = new ArrayList<>();

		if (s.length() >= p.length()) {
			int[] letterToCount = buildLetterToCount(p);
			int[] currentKey = buildLetterToCount(s.substring(0, p.length() - 1));
			for (int i = p.length() - 1; i < s.length(); ++i) {
				++currentKey[s.charAt(i) - 'a'];

				if (Arrays.equals(currentKey, letterToCount)) {
					indices.add(i - p.length() + 1);
				}

				--currentKey[s.charAt(i - p.length() + 1) - 'a'];
			}
		}

		return indices;
	}

	int[] buildLetterToCount(String str) {
		int[] letterToCount = new int[26];
		for (char ch : str.toCharArray()) {
			letterToCount[ch - 'a']++;
		}

		return letterToCount;
	}
}
