public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int maximumMinimumPath(int[][] A) {
		int R = A.length;
		int C = A[0].length;

		int result = -1;
		int lower = 0;
		int upper = Math.min(A[0][0], A[R - 1][C - 1]);
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (isReachable(A, middle, R - 1, C - 1, new boolean[R][C], 0, 0)) {
				result = middle;

				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return result;
	}

	boolean isReachable(int[][] A, int minValue, int targetR, int targetC, boolean[][] visited, int r, int c) {
		int R = A.length;
		int C = A[0].length;

		if (r == targetR && c == targetC) {
			return true;
		}

		visited[r][c] = true;

		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i];
			int nextC = c + C_OFFSETS[i];

			if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && !visited[nextR][nextC]
					&& A[nextR][nextC] >= minValue
					&& isReachable(A, minValue, targetR, targetC, visited, nextR, nextC)) {
				return true;
			}
		}

		return false;
	}
}
