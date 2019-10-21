import java.util.Arrays;

public class Solution {
	static final char[] LETTERS = { 'Q', 'W', 'E', 'R' };

	public int balancedString(String s) {
		int[] a = s.chars().map(ch -> find((char) ch)).toArray();

		int[] counts = new int[LETTERS.length];
		int rightIndex = a.length;
		while (rightIndex > 0) {
			counts[a[rightIndex - 1]]++;

			if (!check(counts, rightIndex - 1)) {
				counts[a[rightIndex - 1]]--;

				break;
			}

			rightIndex--;
		}

		if (rightIndex == 0) {
			return 0;
		}

		int result = rightIndex;
		for (int leftIndex = 0;; leftIndex++) {
			counts[a[leftIndex]]++;

			while (!check(counts, rightIndex - leftIndex - 1)) {
				if (rightIndex == a.length) {
					return result;
				}

				counts[a[rightIndex]]--;
				rightIndex++;
			}

			result = Math.min(result, rightIndex - leftIndex - 1);
		}
	}

	int find(char ch) {
		for (int i = 0;; i++) {
			if (LETTERS[i] == ch) {
				return i;
			}
		}
	}

	boolean check(int[] counts, int freeLength) {
		int max = Arrays.stream(counts).max().getAsInt();

		return Arrays.stream(counts).map(count -> max - count).sum() <= freeLength;
	}
}
