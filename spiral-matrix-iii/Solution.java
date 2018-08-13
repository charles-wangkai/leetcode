public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
		int[][] result = new int[R * C][2];
		int index = 0;

		int maxStep = 1;
		int maxStepCount = 0;
		int direction = 1;
		int step = 0;
		int row = r0;
		int col = c0;
		while (true) {
			if (row >= 0 && row < R && col >= 0 && col < C) {
				result[index][0] = row;
				result[index][1] = col;

				index++;
				if (index == result.length) {
					break;
				}
			}

			if (step == maxStep) {
				step = 0;
				direction = (direction + 1) % R_OFFSETS.length;

				maxStepCount++;
				if (maxStepCount == 2) {
					maxStepCount = 0;
					maxStep++;
				}
			}

			step++;
			row += R_OFFSETS[direction];
			col += C_OFFSETS[direction];
		}
		return result;
	}
}
