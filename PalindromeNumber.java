public class PalindromeNumber {
	public boolean isPalindrome(int x) {
		return isPalindromeString(x + "");
	}

	boolean isPalindromeString(String str) {
		for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
