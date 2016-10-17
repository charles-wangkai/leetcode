import java.util.Arrays;

public class LongestRepeatingCharacterReplacement {
	public int characterReplacement(String s, int k) {
		int[] letterCounts = new int[26];

		int leftIndex = 0;
		int total = 0;
		int max = 0;
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			total++;

			char letter = s.charAt(i);
			setLetterCount(letterCounts, letter, getLetterCount(letterCounts, letter) + 1);

			max = Math.max(max, getLetterCount(letterCounts, letter));

			while (total - max > k) {
				total--;

				char leftLetter = s.charAt(leftIndex);
				setLetterCount(letterCounts, leftLetter, getLetterCount(letterCounts, leftLetter) - 1);
				leftIndex++;

				max = Arrays.stream(letterCounts).max().getAsInt();
			}

			result = Math.max(result, total);
		}

		return result;
	}

	int getLetterCount(int[] letterCounts, char letter) {
		return letterCounts[letter - 'A'];
	}

	void setLetterCount(int[] letterCounts, char letter, int letterCount) {
		letterCounts[letter - 'A'] = letterCount;
	}
}
