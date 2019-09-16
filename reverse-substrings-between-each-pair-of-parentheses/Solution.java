public class Solution {
	public String reverseParentheses(String s) {
		StringBuilder result = new StringBuilder();
		int depth = 0;
		int beginIndex = -1;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == '(') {
				if (depth == 0) {
					beginIndex = i;
				}

				depth++;
			} else if (ch == ')') {
				depth--;

				if (depth == 0) {
					result.append(new StringBuilder(reverseParentheses(s.substring(beginIndex + 1, i))).reverse());
				}
			} else if (depth == 0) {
				result.append(ch);
			}
		}

		return result.toString();
	}
}
