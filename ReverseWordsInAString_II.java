public class ReverseWordsInAString_II {
	public void reverseWords(char[] s) {
		reverse(s, 0, s.length - 1);

		int wordBegin = 0;
		for (int i = 0; i <= s.length; i++) {
			if (i == s.length || s[i] == ' ') {
				reverse(s, wordBegin, i - 1);
				wordBegin = i + 1;
			}
		}
	}

	void reverse(char[] s, int begin, int end) {
		for (int i = begin, j = end; i < j; i++, j--) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
		}
	}
}
