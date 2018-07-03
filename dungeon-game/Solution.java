public class Solution {
	public int calculateMinimumHP(int[][] dungeon) {
		int row = dungeon.length;
		int col = dungeon[0].length;
		int[][] minHPs = new int[row][col];
		for (int i = row - 1; i >= 0; i--) {
			for (int j = col - 1; j >= 0; j--) {
				if (i == row - 1 && j == col - 1) {
					minHPs[i][j] = (dungeon[i][j] < 0) ? (-dungeon[i][j] + 1)
							: 1;
					continue;
				}
				int right = (j == col - 1) ? Integer.MAX_VALUE
						: minHPs[i][j + 1];
				int down = (i == row - 1) ? Integer.MAX_VALUE
						: minHPs[i + 1][j];
				int minNext = Math.min(right, down);
				minHPs[i][j] = Math.max(minNext - dungeon[i][j], 1);
			}
		}
		return minHPs[0][0];
	}
}
