public class AddBoldTagInString {
	public String addBoldTag(String s, String[] dict) {
		boolean[] bolds = new boolean[s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (String word : dict) {
				if (s.startsWith(word, i)) {
					for (int j = i; j < i + word.length(); j++) {
						bolds[j] = true;
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bolds.length; i++) {
			if (bolds[i] && (i == 0 || !bolds[i - 1])) {
				sb.append("<b>");
			}

			sb.append(s.charAt(i));

			if (bolds[i] && (i == bolds.length - 1 || !bolds[i + 1])) {
				sb.append("</b>");
			}
		}
		return sb.toString();
	}
}
