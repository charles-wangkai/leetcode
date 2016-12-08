import java.util.Arrays;

public class UniqueSubstringsInWraparoundString {
	public int findSubstringInWraproundString(String p) {
		int[] maxLengths = new int[26];
		int startIndex = 0;
		for (int i = 0; i <= p.length(); i++) {
			if (i == p.length() || p.charAt(i) != getTargetLetter(p.charAt(startIndex), i - startIndex)) {
				for (int j = 0, length = i - startIndex; j < 26 && length > 0; j++, length--) {
					int index = getTargetLetter(p.charAt(startIndex), j) - 'a';
					maxLengths[index] = Math.max(maxLengths[index], length);
				}

				startIndex = i;
			}
		}
		return Arrays.stream(maxLengths).sum();
	}

	char getTargetLetter(char startLetter, int offset) {
		return (char) ((startLetter - 'a' + offset) % 26 + 'a');
	}
}
