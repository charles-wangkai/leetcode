public class Solution {
	static final int MOD_DIVISOR = 1_000_000_007;

	public int numMusicPlaylists(int N, int L, int K) {
		int[][] ways = new int[N + 1][L + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= L; j++) {
				if (j == 0) {
					if (i == 0) {
						ways[i][j] = 1;
					} else {
						ways[i][j] = 0;
					}
				} else {
					if (i == 0) {
						ways[i][j] = 0;
					} else {
						ways[i][j] = addMod(multiplyMod(ways[i - 1][j - 1], i),
								multiplyMod(ways[i][j - 1], Math.max(0, i - K)));
					}
				}
			}
		}
		return ways[N][L];
	}

	int addMod(int x, int y) {
		return (x + y) % MOD_DIVISOR;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MOD_DIVISOR);
	}
}
