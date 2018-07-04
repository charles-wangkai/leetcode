public class Solution {
	public int minCost(int[][] costs) {
		int[][] minCosts = new int[costs.length + 1][3];
		for (int i = 1; i < minCosts.length; i++) {
			for (int j = 0; j < 3; j++) {
				minCosts[i][j] = Math.min(minCosts[i - 1][(j + 1) % 3]
						+ costs[i - 1][j], minCosts[i - 1][(j + 2) % 3]
						+ costs[i - 1][j]);
			}
		}
		return Math.min(Math.min(minCosts[minCosts.length - 1][0],
				minCosts[minCosts.length - 1][1]),
				minCosts[minCosts.length - 1][2]);
	}
}
