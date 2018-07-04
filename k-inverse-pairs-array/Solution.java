public class Solution {
	final int MODULUS = 1000000007;

	public int kInversePairs(int n, int k) {
		int[][] ways = new int[n + 1][k + 1];
		ways[1][0] = 1;
		for (int i = 2; i <= n; i++) {
			int sum = 0;
			for (int j = 0; j <= k; j++) {
				sum = addMod(sum, ways[i - 1][j]);

				if (j >= i) {
					sum = subtractMod(sum, ways[i - 1][j - i]);
				}

				ways[i][j] = sum;
			}
		}
		return ways[n][k];
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}

	int subtractMod(int x, int y) {
		return addMod(x, MODULUS - y);
	}
}
