import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int snakesAndLadders(int[][] board) {
		int[] destinations = flatten(board);

		int[] moveNums = new int[destinations.length];
		Arrays.fill(moveNums, -1);
		moveNums[0] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int i = current + 1; i <= current + 6 && i < destinations.length; i++) {
				int next = (destinations[i] == -1) ? i : destinations[i];

				if (moveNums[next] == -1) {
					moveNums[next] = moveNums[current] + 1;

					if (next == destinations.length - 1) {
						return moveNums[next];
					}

					queue.offer(next);
				}
			}
		}
		return -1;
	}

	int[] flatten(int[][] board) {
		int N = board.length;
		int r = N - 1;
		int c = -1;
		int offsetC = 1;
		int[] destinations = new int[N * N];
		for (int i = 0; i < destinations.length; i++) {
			c += offsetC;
			if (c == N || c == -1) {
				r--;
				offsetC *= -1;

				if (c == N) {
					c = N - 1;
				} else {
					c = 0;
				}
			}

			destinations[i] = (board[r][c] == -1) ? -1 : (board[r][c] - 1);
		}
		return destinations;
	}
}
