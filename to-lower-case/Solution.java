public class Solution {
	public String toLowerCase(String str) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				result.append((char) (ch - 'A' + 'a'));
			} else {
				result.append(ch);
			}
		}
		return result.toString();
	}
}
