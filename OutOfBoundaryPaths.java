public class OutOfBoundaryPaths {
	static final int MODULUS = 1000000007;
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int findPaths(int m, int n, int N, int i, int j) {
		int result = 0;

		int[][] pathNums = new int[m][n];
		for (int c = 0; c < n; c++) {
			pathNums[0][c] = addMod(pathNums[0][c], 1);
			pathNums[m - 1][c] = addMod(pathNums[m - 1][c], 1);
		}
		for (int r = 0; r < m; r++) {
			pathNums[r][0] = addMod(pathNums[r][0], 1);
			pathNums[r][n - 1] = addMod(pathNums[r][n - 1], 1);
		}

		for (int k = 0; k < N; k++) {
			result = addMod(result, pathNums[i][j]);

			int[][] nextPathNums = new int[m][n];
			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					for (int p = 0; p < R_OFFSETS.length; p++) {
						int adjR = r + R_OFFSETS[p];
						int adjC = c + C_OFFSETS[p];
						if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
							nextPathNums[r][c] = addMod(nextPathNums[r][c], pathNums[adjR][adjC]);
						}
					}
				}
			}
			pathNums = nextPathNums;
		}

		return result;
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}
