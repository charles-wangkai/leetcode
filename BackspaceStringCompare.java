public class BackspaceStringCompare {
	public boolean backspaceCompare(String S, String T) {
		return edit(S).equals(edit(T));
	}

	String edit(String input) {
		StringBuilder result = new StringBuilder();
		for (char ch : input.toCharArray()) {
			if (ch == '#') {
				if (result.length() > 0) {
					result.deleteCharAt(result.length() - 1);
				}
			} else {
				result.append(ch);
			}
		}
		return result.toString();
	}
}
