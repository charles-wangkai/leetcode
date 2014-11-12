import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
	static final int[] OFFSET_R = { 0, 1, 0, -1 };
	static final int[] OFFSET_C = { 1, 0, -1, 0 };
	int r;
	int c;

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		int row = matrix.length;
		if (row > 0) {
			int col = matrix[0].length;
			if (col > 0) {
				r = 0;
				c = -1;
				int direction = 0;
				for (int sizeC = col, sizeR = row; sizeC > 0 && sizeR > 0; sizeC -= 2, sizeR -= 2) {
					move(matrix, result, direction, sizeC);
					if (sizeR == 1) {
						break;
					}
					direction = turn(direction);
					move(matrix, result, direction, sizeR - 1);
					if (sizeC == 1) {
						break;
					}
					direction = turn(direction);
					move(matrix, result, direction, sizeC - 1);
					direction = turn(direction);
					move(matrix, result, direction, sizeR - 2);
					direction = turn(direction);
				}
			}
		}
		return result;
	}

	void move(int[][] matrix, List<Integer> result, int direction, int step) {
		for (int i = 0; i < step; i++) {
			r += OFFSET_R[direction];
			c += OFFSET_C[direction];
			result.add(matrix[r][c]);
		}
	}

	int turn(int direction) {
		return (direction + 1) % 4;
	}
}
