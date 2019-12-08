import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int minFlips(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;

		int state = encode(mat);
		Map<Integer, Integer> stateToDistance = new HashMap<>();
		stateToDistance.put(state, 0);
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(state);

		while (!queue.isEmpty()) {
			int head = queue.poll();
			if (head == 0) {
				return stateToDistance.get(head);
			}

			int[][] matrix = decode(m, n, head);
			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					int nextState = encode(flip(matrix, r, c));

					if (!stateToDistance.containsKey(nextState)) {
						stateToDistance.put(nextState, stateToDistance.get(head) + 1);
						queue.offer(nextState);
					}
				}
			}
		}

		return -1;
	}

	int encode(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		int state = 0;
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				state = state * 2 + matrix[r][c];
			}
		}

		return state;
	}

	int[][] decode(int m, int n, int state) {
		int[][] matrix = new int[m][n];
		for (int r = m - 1; r >= 0; r--) {
			for (int c = n - 1; c >= 0; c--) {
				matrix[r][c] = state % 2;
				state /= 2;
			}
		}

		return matrix;
	}

	int[][] flip(int[][] matrix, int centerR, int centerC) {
		int m = matrix.length;
		int n = matrix[0].length;

		int[][] result = new int[m][n];
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (Math.abs(r - centerR) + Math.abs(c - centerC) <= 1) {
					result[r][c] = 1 - matrix[r][c];
				} else {
					result[r][c] = matrix[r][c];
				}
			}
		}

		return result;
	}
}
