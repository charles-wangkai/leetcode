import java.util.Arrays;

public class Solution {
	public int maxScoreWords(String[] words, char[] letters, int[] score) {
		int[] counts = new int[26];
		for (char letter : letters) {
			counts[letter - 'a']++;
		}

		int result = 0;
		for (int code = 0; code < 1 << words.length; code++) {
			int[] remains = Arrays.copyOf(counts, counts.length);
			int sum = 0;
			boolean valid = true;
			boolean[] used = decode(words.length, code);
			for (int i = 0; i < used.length; i++) {
				if (used[i]) {
					for (char ch : words[i].toCharArray()) {
						if (remains[ch - 'a'] == 0) {
							valid = false;

							break;
						}

						remains[ch - 'a']--;
						sum += score[ch - 'a'];
					}

					if (!valid) {
						break;
					}
				}
			}

			if (valid) {
				result = Math.max(result, sum);
			}
		}

		return result;
	}

	boolean[] decode(int size, int code) {
		boolean[] result = new boolean[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = (code & 1) != 0;

			code >>= 1;
		}

		return result;
	}
}
