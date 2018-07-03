public class Solution {
	public String decodeString(String s) {
		int level = 0;
		int startIndex = -1;
		int k = 0;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '[') {
				if (level == 0) {
					startIndex = i;
				}

				level++;
			} else if (ch == ']') {
				level--;

				if (level == 0) {
					sb.append(repeat(decodeString(s.substring(startIndex + 1, i)), k));
					k = 0;
				}
			} else if (Character.isDigit(ch)) {
				if (level == 0) {
					k = k * 10 + (ch - '0');
				}
			} else if (level == 0) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	String repeat(String str, int k) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
}
