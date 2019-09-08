public class Solution {
	public int countLetters(String S) {
		int result = 0;
		char prev = 0;
		int count = 0;
		for (int i = 0; i <= S.length(); i++) {
			if (i != S.length() && S.charAt(i) == prev) {
				count++;
			} else {
				result += count * (count + 1) / 2;

				if (i != S.length()) {
					prev = S.charAt(i);
					count = 1;
				}
			}
		}

		return result;
	}
}
