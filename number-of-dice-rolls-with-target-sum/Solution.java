public class Solution {
	static final int MODULUS = 1_000_000_007;

	public int numRollsToTarget(int d, int f, int target) {
		int[] ways = new int[target + 1];
		ways[0] = 1;

		for (int i = 0; i < d; i++) {
			int[] nextWays = new int[ways.length];
			for (int j = 0; j < nextWays.length; j++) {
				for (int k = 1; k <= f && k <= j; k++) {
					nextWays[j] = addMod(nextWays[j], ways[j - k]);
				}
			}

			ways = nextWays;
		}

		return ways[target];
	}

	static int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}
