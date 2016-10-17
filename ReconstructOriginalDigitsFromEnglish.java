public class ReconstructOriginalDigitsFromEnglish {
	final String[] REPRESENTATIONS = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
	final int[] SEQUENCE = { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };
	final char[] KEY_LETTERS = { 'z', 'o', 'w', 'r', 'u', 'f', 'x', 's', 'g', 'i' };

	public String originalDigits(String s) {
		int[] letterCounts = buildLetterCounts(s);

		int[] digitCounts = new int[10];

		for (int digit : SEQUENCE) {
			digitCounts[digit] = getLetterCount(letterCounts, KEY_LETTERS[digit]);

			remove(letterCounts, REPRESENTATIONS[digit], digitCounts[digit]);
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < digitCounts.length; i++) {
			for (int j = 0; j < digitCounts[i]; j++) {
				result.append(i);
			}
		}
		return result.toString();
	}

	void remove(int[] letterCounts, String representation, int count) {
		for (char letter : representation.toCharArray()) {
			setLetterCount(letterCounts, letter, getLetterCount(letterCounts, letter) - count);
		}
	}

	int[] buildLetterCounts(String s) {
		int[] letterCounts = new int[26];

		for (char letter : s.toCharArray()) {
			setLetterCount(letterCounts, letter, getLetterCount(letterCounts, letter) + 1);
		}

		return letterCounts;
	}

	int getLetterCount(int[] letterCounts, char letter) {
		return letterCounts[letter - 'a'];
	}

	void setLetterCount(int[] letterCounts, char letter, int letterCount) {
		letterCounts[letter - 'a'] = letterCount;
	}
}
