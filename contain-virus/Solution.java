public class Solution {
	int[] R_OFFSETS = { -1, 0, 1, 0 };
	int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int containVirus(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		boolean[][] walls = new boolean[row * 2 - 1][col];
		int wallNum = 0;
		while (true) {
			int maxAffectNum = 0;
			int seedR = -1;
			int seedC = -1;

			boolean[][] visited = new boolean[row][col];
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < col; c++) {
					if (!visited[r][c] && grid[r][c] == 1) {
						int affectNum = countAffectNum(visited, new boolean[row][col], grid, walls, r, c);
						if (affectNum > maxAffectNum) {
							maxAffectNum = affectNum;
							seedR = r;
							seedC = c;
						}
					}
				}
			}

			if (maxAffectNum == 0) {
				break;
			}

			wallNum += buildWalls(new boolean[row][col], grid, walls, seedR, seedC);

			grid = spread(grid, walls);
		}
		return wallNum;
	}

	int[][] spread(int[][] grid, boolean[][] walls) {
		int row = grid.length;
		int col = grid[0].length;

		int[][] result = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				result[r][c] = grid[r][c];
			}
		}
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] == 1) {
					for (int i = 0; i < R_OFFSETS.length; i++) {
						int adjR = r + R_OFFSETS[i];
						int adjC = c + C_OFFSETS[i];

						if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && isConnected(r, c, adjR, adjC, walls)
								&& grid[adjR][adjC] == 0) {
							result[adjR][adjC] = 1;
						}
					}
				}
			}
		}
		return result;
	}

	int buildWalls(boolean[][] visited, int[][] grid, boolean[][] walls, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;

		visited[r][c] = true;

		int wallNum = 0;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjR = r + R_OFFSETS[i];
			int adjC = c + C_OFFSETS[i];
			if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && isConnected(r, c, adjR, adjC, walls)) {
				if (grid[adjR][adjC] == 1) {
					if (!visited[adjR][adjC]) {
						wallNum += buildWalls(visited, grid, walls, adjR, adjC);
					}
				} else {
					buildWall(r, c, adjR, adjC, walls);
					wallNum++;
				}
			}
		}
		return wallNum;
	}

	int countAffectNum(boolean[][] visited, boolean[][] affected, int[][] grid, boolean[][] walls, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;

		visited[r][c] = true;

		int affectNum = 0;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjR = r + R_OFFSETS[i];
			int adjC = c + C_OFFSETS[i];
			if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && isConnected(r, c, adjR, adjC, walls)) {
				if (grid[adjR][adjC] == 1) {
					if (!visited[adjR][adjC]) {
						affectNum += countAffectNum(visited, affected, grid, walls, adjR, adjC);
					}
				} else {
					if (!affected[adjR][adjC]) {
						affected[adjR][adjC] = true;
						affectNum++;
					}
				}
			}
		}
		return affectNum;
	}

	boolean isConnected(int r1, int c1, int r2, int c2, boolean[][] walls) {
		if (r1 == r2) {
			return !walls[r1 * 2][Math.min(c1, c2)];
		} else {
			return !walls[Math.min(r1, r2) * 2 + 1][c1];
		}
	}

	void buildWall(int r1, int c1, int r2, int c2, boolean[][] walls) {
		if (r1 == r2) {
			walls[r1 * 2][Math.min(c1, c2)] = true;
		} else {
			walls[Math.min(r1, r2) * 2 + 1][c1] = true;
		}
	}
}
