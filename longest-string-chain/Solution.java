import java.util.Arrays;

public class Solution {
	public int longestStrChain(String[] words) {
		Arrays.sort(words, (word1, word2) -> Integer.compare(word1.length(), word2.length()));

		int[] lengths = new int[words.length];
		for (int i = 0; i < lengths.length; i++) {
			lengths[i] = 1;

			for (int j = 0; j < i; j++) {
				if (isPredecessor(words[j], words[i])) {
					lengths[i] = Math.max(lengths[i], lengths[j] + 1);
				}
			}
		}

		return Arrays.stream(lengths).max().getAsInt();
	}

	boolean isPredecessor(String word1, String word2) {
		if (word1.length() + 1 != word2.length()) {
			return false;
		}

		boolean inserted = false;
		int index1 = 0;
		int index2 = 0;
		while (index1 < word1.length() && index2 < word2.length()) {
			if (word1.charAt(index1) == word2.charAt(index2)) {
				index1++;
			} else {
				if (inserted) {
					return false;
				}
				inserted = true;
			}

			index2++;
		}

		return true;
	}
}
