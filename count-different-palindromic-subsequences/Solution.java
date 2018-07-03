import java.util.Arrays;

public class Solution {
	static final int MODULUS = 1000000007;
	static final int ALPHABET_SIZE = 4;

	static int[][] prev;
	static int[][] next;
	static int[][] cache;

	public int countPalindromicSubsequences(String S) {
		int length = S.length();

		prev = buildAdjacents(S, 0, 1);
		next = buildAdjacents(S, length - 1, -1);

		cache = new int[length][length];
		for (int i = 0; i < cache.length; i++) {
			Arrays.fill(cache[i], -1);
		}

		return search(S, 0, length - 1) - 1;
	}

	int search(String S, int beginIndex, int endIndex) {
		if (cache[beginIndex][endIndex] >= 0) {
			return cache[beginIndex][endIndex];
		}

		int result = 1;
		if (beginIndex <= endIndex) {
			for (int i = 0; i < ALPHABET_SIZE; i++) {
				int left = next[beginIndex][i];
				int right = prev[endIndex][i];

				if (left >= 0) {
					if (left <= endIndex) {
						result = addMod(result, 1);
					}

					if (left < right) {
						result = addMod(result, search(S, left + 1, right - 1));
					}
				}
			}
		}
		cache[beginIndex][endIndex] = result;
		return result;
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}

	int[][] buildAdjacents(String S, int index, int offset) {
		int[] last = new int[ALPHABET_SIZE];
		int[][] adjacents = new int[S.length()][last.length];
		Arrays.fill(last, -1);
		while (index >= 0 && index < S.length()) {
			last[S.charAt(index) - 'a'] = index;

			for (int i = 0; i < last.length; i++) {
				adjacents[index][i] = last[i];
			}

			index += offset;
		}
		return adjacents;
	}
}
