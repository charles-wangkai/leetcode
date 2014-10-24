import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		Set<Character> charSet = new HashSet<Character>();
		int maxLength = 0;
		int leftIndex = 0;
		for (int rightIndex = 0; rightIndex < s.length(); rightIndex++) {
			char ch = s.charAt(rightIndex);
			while (charSet.contains(ch)) {
				charSet.remove(s.charAt(leftIndex));
				leftIndex++;
			}
			charSet.add(ch);
			maxLength = Math.max(maxLength, rightIndex - leftIndex + 1);
		}
		return maxLength;
	}
}
