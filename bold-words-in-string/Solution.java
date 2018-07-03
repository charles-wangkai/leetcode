public class Solution {
	public String boldWords(String[] words, String S) {
		boolean[] bolds = new boolean[S.length()];
		for (int i = 0; i < S.length(); i++) {
			for (String word : words) {
				if (S.startsWith(word, i)) {
					for (int j = i; j < i + word.length(); j++) {
						bolds[j] = true;
					}
				}
			}
		}

		StringBuilder result = new StringBuilder();
		StringBuilder boldPart = new StringBuilder();
		for (int i = 0; i <= S.length(); i++) {
			if (i < S.length() && bolds[i]) {
				boldPart.append(S.charAt(i));
			} else {
				if (boldPart.length() > 0) {
					result.append("<b>").append(boldPart).append("</b>");
					boldPart = new StringBuilder();
				}

				if (i < S.length()) {
					result.append(S.charAt(i));
				}
			}
		}

		return result.toString();
	}
}
