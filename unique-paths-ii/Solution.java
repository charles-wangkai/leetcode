public class Solution {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int row = obstacleGrid.length;
		int col = obstacleGrid[0].length;
		int[][] pathNums = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (obstacleGrid[i][j] == 0) {
					pathNums[i][j] = (i == 0 && j == 0) ? 1 : ((i == 0 ? 0
							: pathNums[i - 1][j]) + (j == 0 ? 0
							: pathNums[i][j - 1]));
				}
			}
		}
		return pathNums[row - 1][col - 1];
	}
}
