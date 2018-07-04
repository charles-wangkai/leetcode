public class Solution {
	public boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (true) {
			while (i < s.length() && !isCheckableChar(s.charAt(i))) {
				i++;
			}
			while (j >= 0 && !isCheckableChar(s.charAt(j))) {
				j--;
			}
			if (i >= j) {
				return true;
			}
			if (!isSameIgnoringCase(s.charAt(i), s.charAt(j))) {
				return false;
			}
			i++;
			j--;
		}
	}

	boolean isCheckableChar(char ch) {
		return Character.isLetterOrDigit(ch);
	}

	boolean isSameIgnoringCase(char ch1, char ch2) {
		return Character.toLowerCase(ch1) == Character.toLowerCase(ch2);
	}
}
