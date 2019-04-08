public class Solution {
	public String removeOuterParentheses(String S) {
		StringBuilder result = new StringBuilder();
		int beginIndex = 0;
		int depth = 0;
		for (int endIndex = 0; endIndex < S.length(); endIndex++) {
			if (S.charAt(endIndex) == '(') {
				depth++;
			} else {
				depth--;

				if (depth == 0) {
					result.append(S.substring(beginIndex + 1, endIndex));

					beginIndex = endIndex + 1;
				}
			}
		}
		return result.toString();
	}
}
