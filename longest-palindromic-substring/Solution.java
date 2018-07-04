public class Solution {
	String result;

	public String longestPalindrome(String s) {
		result = "";
		for (int i = 0; i < s.length(); i++) {
			compareAndUpdateResult(findMaxLengthPalindrome(s, i, i));
		}
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				compareAndUpdateResult(findMaxLengthPalindrome(s, i, i + 1));
			}
		}
		return result;
	}

	String findMaxLengthPalindrome(String s, int leftIndex, int rightIndex) {
		while (leftIndex > 0 && rightIndex < s.length() - 1
				&& s.charAt(leftIndex - 1) == s.charAt(rightIndex + 1)) {
			leftIndex--;
			rightIndex++;
		}
		return s.substring(leftIndex, rightIndex + 1);
	}

	void compareAndUpdateResult(String palindrome) {
		if (palindrome.length() > result.length()) {
			result = palindrome;
		}
	}
}
