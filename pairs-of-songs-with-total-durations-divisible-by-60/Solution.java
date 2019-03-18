public class Solution {
	static final int MOD_DIVISOR = 60;

	public int numPairsDivisibleBy60(int[] time) {
		int[] remainders = new int[MOD_DIVISOR];
		for (int x : time) {
			remainders[x % MOD_DIVISOR]++;
		}

		int result = 0;
		for (int i = 0; i * 2 <= MOD_DIVISOR; i++) {
			int other = (MOD_DIVISOR - i) % MOD_DIVISOR;
			if (i == other) {
				result += remainders[i] * (remainders[i] - 1L) / 2;
			} else {
				result += remainders[i] * remainders[other];
			}
		}
		return result;
	}
}
