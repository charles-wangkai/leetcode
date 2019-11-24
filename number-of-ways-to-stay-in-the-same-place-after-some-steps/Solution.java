public class Solution {
	static final int MODULUS = 1_000_000_007;

	public int numWays(int steps, int arrLen) {
		int[] wayNums = new int[Math.min(arrLen, steps / 2 + 1)];
		wayNums[0] = 1;

		for (int step = 0; step < steps; step++) {
			int[] nextWayNums = new int[wayNums.length];
			for (int i = 0; i < nextWayNums.length; i++) {
				for (int offset = -1; offset <= 1; offset++) {
					int prevIndex = i + offset;
					if (prevIndex >= 0 && prevIndex < wayNums.length) {
						nextWayNums[i] = addMod(nextWayNums[i], wayNums[prevIndex]);
					}
				}
			}

			wayNums = nextWayNums;
		}

		return wayNums[0];
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}
