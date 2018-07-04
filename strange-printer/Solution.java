public class Solution {
	static final int LIMIT = 100;

	int[][] cache;

	public int strangePrinter(String s) {
		cache = new int[LIMIT][LIMIT];

		return search(s, 0, s.length() - 1);
	}

	int search(String s, int left, int right) {
		if (left > right) {
			return 0;
		}

		if (cache[left][right] != 0) {
			return cache[left][right];
		}

		int result = search(s, left, right - 1) + 1;
		for (int i = left; i < right; i++) {
			if (s.charAt(i) == s.charAt(right)) {
				result = Math.min(result, search(s, left, i) + search(s, i + 1, right - 1));
			}
		}

		cache[left][right] = result;
		return result;
	}
}
