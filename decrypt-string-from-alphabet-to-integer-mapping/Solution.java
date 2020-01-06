public class Solution {
	public String freqAlphabets(String s) {
		StringBuilder result = new StringBuilder();
		int beginIndex = 0;
		while (beginIndex != s.length()) {
			for (int i = 26; i >= 1; --i) {
				String token = String.format("%d%s", i, (i >= 10) ? "#" : "");
				if (s.startsWith(token, beginIndex)) {
					result.append((char) ('a' + i - 1));
					beginIndex += token.length();

					break;
				}
			}
		}

		return result.toString();
	}
}
