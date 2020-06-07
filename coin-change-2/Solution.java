public class Solution {
	public int change(int amount, int[] coins) {
		if (coins.length == 0) {
			return amount == 0 ? 1 : 0;
		}

		int[][] ways = new int[amount + 1][coins.length];
		for (int i = 0; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i == 0 && j == 0) {
					ways[i][j] = 1;
				} else {
					ways[i][j] = (j == 0 ? 0 : ways[i][j - 1]) + (i >= coins[j] ? ways[i - coins[j]][j] : 0);
				}
			}
		}

		return ways[amount][coins.length - 1];
	}
}
