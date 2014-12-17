public class ReverseWordsInAString {
	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		int wordEnd = -1;
		for (int i = s.length() - 1; i + 1 >= 0; i--) {
			if (i < 0 || s.charAt(i) == ' ') {
				if (wordEnd >= 0) {
					String word = s.substring(i + 1, wordEnd + 1);
					if (sb.length() != 0) {
						sb.append(" ");
					}
					sb.append(word);
				}
				wordEnd = -1;
			} else if (wordEnd < 0) {
				wordEnd = i;
			}
		}
		return sb.toString();
	}
}
