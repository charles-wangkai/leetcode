import java.util.Arrays;

public class Solution {
	public int coinChange(int[] coins, int amount) {
		int[] minCoinNums = new int[amount + 1];
		minCoinNums[0] = 0;
		Arrays.fill(minCoinNums, 1, minCoinNums.length, -1);

		for (int coin : coins) {
			for (int i = 0; i < minCoinNums.length; i++) {
				if (i >= coin && minCoinNums[i - coin] != -1
						&& (minCoinNums[i] == -1 || minCoinNums[i] > minCoinNums[i - coin] + 1)) {
					minCoinNums[i] = minCoinNums[i - coin] + 1;
				}
			}
		}

		return minCoinNums[amount];
	}
}
