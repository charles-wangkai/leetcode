public class Solution {
	static final int MODULUS = 1_000_000_007;

	public int numberOfWays(int num_people) {
		int ways[] = new int[num_people + 1];
		ways[0] = 1;
		for (int i = 2; i < ways.length; i += 2) {
			for (int j = 0; j <= i - 2; j++) {
				ways[i] = addMod(ways[i], multiplyMod(ways[j], ways[i - 2 - j]));
			}
		}

		return ways[num_people];
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MODULUS);
	}
}
