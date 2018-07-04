public class Solution {
	static final char SEPARATOR = 0;

	public String shortestPalindrome(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append(SEPARATOR);
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			sb.append(SEPARATOR);
		}

		int[] p = new int[sb.length()];
		int maxRight = -1;
		int centreForMaxRight = -1;
		for (int i = 0; i < p.length; i++) {
			if (maxRight > i) {
				p[i] = Math.min(p[centreForMaxRight * 2 - i], maxRight - i);
			} else {
				p[i] = 1;
			}
			while (i - p[i] >= 0 && i + p[i] < sb.length()
					&& sb.charAt(i - p[i]) == sb.charAt(i + p[i])) {
				p[i]++;
			}
			if (i + p[i] > maxRight) {
				maxRight = i + p[i];
				centreForMaxRight = i;
			}
		}

		int frontPalindromeMaxLength = 0;
		for (int i = 0; i < p.length; i++) {
			if (i - p[i] == -1) {
				frontPalindromeMaxLength = i;
			}
		}
		return new StringBuilder(s.substring(frontPalindromeMaxLength))
				.reverse().toString() + s;
	}
}
