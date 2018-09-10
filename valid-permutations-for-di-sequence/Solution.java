public class Solution {
	static final int MOD_DIVISOR = 1_000_000_007;

	public int numPermsDISequence(String S) {
		int[][] ways = new int[S.length() + 1][];
		ways[S.length()] = new int[] { 1 };

		for (int beginIndex = S.length() - 1; beginIndex >= 0; beginIndex--) {
			ways[beginIndex] = new int[S.length() + 1 - beginIndex];

			if (S.charAt(beginIndex) == 'I') {
				for (int order = 0; order < ways[beginIndex].length - 1; order++) {
					for (int nextOrder = order; nextOrder < ways[beginIndex + 1].length; nextOrder++) {
						ways[beginIndex][order] = addMod(ways[beginIndex][order], ways[beginIndex + 1][nextOrder]);
					}
				}
			} else {
				for (int order = 1; order < ways[beginIndex].length; order++) {
					for (int nextOrder = 0; nextOrder < order; nextOrder++) {
						ways[beginIndex][order] = addMod(ways[beginIndex][order], ways[beginIndex + 1][nextOrder]);
					}
				}
			}
		}

		int result = 0;
		for (int order = 0; order < ways[0].length; order++) {
			result = addMod(result, ways[0][order]);
		}
		return result;
	}

	static int addMod(int x, int y) {
		return (x + y) % MOD_DIVISOR;
	}
}
