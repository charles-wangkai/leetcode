public class Solution {
	public boolean isStrobogrammatic(String num) {
		for (int i = 0, j = num.length() - 1; i <= j; i++, j--) {
			if (!isStrobogrammatic(num.charAt(i), num.charAt(j))) {
				return false;
			}
		}
		return true;
	}

	boolean isStrobogrammatic(char ch1, char ch2) {
		return (ch1 == ch2 && (ch1 == '0' || ch1 == '1' || ch1 == '8'))
				|| (ch1 == '6' && ch2 == '9') || (ch1 == '9' && ch2 == '6');
	}
}
