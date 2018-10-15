public class Solution {
	public int minAddToMakeValid(String S) {
		int result = 0;
		int depth = 0;
		for (char ch : S.toCharArray()) {
			if (ch == '(') {
				depth++;
			} else {
				if (depth > 0) {
					depth--;
				} else {
					result++;
				}
			}
		}
		result += depth;
		return result;
	}
}
