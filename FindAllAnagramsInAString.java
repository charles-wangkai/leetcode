import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> indices = new ArrayList<Integer>();

		if (s.length() >= p.length()) {
			int[] letter2count = buildLetter2count(p);
			int[] currentKey = buildLetter2count(s.substring(0, p.length() - 1));
			for (int i = p.length() - 1; i < s.length(); i++) {
				currentKey[convertToIndex(s.charAt(i))]++;

				if (Arrays.equals(currentKey, letter2count)) {
					indices.add(i - p.length() + 1);
				}

				currentKey[convertToIndex(s.charAt(i - p.length() + 1))]--;
			}
		}

		return indices;
	}

	int[] buildLetter2count(String str) {
		int[] letter2count = new int[26];
		for (int i = 0; i < str.length(); i++) {
			letter2count[convertToIndex(str.charAt(i))]++;
		}
		return letter2count;
	}

	int convertToIndex(char ch) {
		return ch - 'a';
	}
}
