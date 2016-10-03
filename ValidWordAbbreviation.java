public class ValidWordAbbreviation {
	public boolean validWordAbbreviation(String word, String abbr) {
		int wordIndex = 0;
		int abbrIndex = 0;

		while (abbrIndex < abbr.length()) {
			char ch = abbr.charAt(abbrIndex);
			if (Character.isDigit(ch)) {
				if (ch == '0') {
					return false;
				}

				int number = 0;
				while (abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))) {
					number = number * 10 + (abbr.charAt(abbrIndex) - '0');
					abbrIndex++;
				}

				if (wordIndex + number > word.length()) {
					return false;
				}
				wordIndex += number;
			} else {
				if (wordIndex == word.length() || word.charAt(wordIndex) != ch) {
					return false;
				}

				abbrIndex++;
				wordIndex++;
			}
		}

		return wordIndex == word.length();
	}
}
