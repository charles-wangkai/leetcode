public class Solution {
	public int longestDecomposition(String text) {
		if (text.isEmpty()) {
			return 0;
		}

		for (int i = 1; i * 2 <= text.length(); i++) {
			if (text.substring(0, i).equals(text.substring(text.length() - i))) {
				return 2 + longestDecomposition(text.substring(i, text.length() - i));
			}
		}

		return 1;
	}
}
