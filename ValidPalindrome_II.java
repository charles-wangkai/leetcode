public class ValidPalindrome_II {
	public boolean validPalindrome(String s) {
		int lower = 0;
		int upper = s.length() - 1;
		while (lower < upper && s.charAt(lower) == s.charAt(upper)) {
			lower++;
			upper--;
		}

		if (lower >= upper) {
			return true;
		}

		return isPalindrome(s, lower + 1, upper) || isPalindrome(s, lower, upper - 1);
	}

	boolean isPalindrome(String s, int lower, int upper) {
		for (int i = lower, j = upper; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
