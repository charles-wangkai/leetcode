public class PalindromicSubstrings {
	public int countSubstrings(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i, k = i; j >= 0 && k < s.length(); j--, k++) {
				if (s.charAt(j) != s.charAt(k)) {
					break;
				}
				result++;
			}

			for (int j = i, k = i + 1; j >= 0 && k < s.length(); j--, k++) {
				if (s.charAt(j) != s.charAt(k)) {
					break;
				}
				result++;
			}
		}
		return result;
	}
}
