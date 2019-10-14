import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	static final int SIZE = 8;
	static final int[] R_OFFSETS = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] C_OFFSETS = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		boolean[][] occupied = new boolean[SIZE][SIZE];
		for (int[] queen : queens) {
			occupied[queen[0]][queen[1]] = true;
		}

		return IntStream.range(0, R_OFFSETS.length)
				.mapToObj(i -> findAttackedQueen(occupied, king, R_OFFSETS[i], C_OFFSETS[i])).filter(x -> x != null)
				.collect(Collectors.toList());
	}

	List<Integer> findAttackedQueen(boolean[][] occupied, int[] king, int rOffset, int cOffset) {
		int r = king[0];
		int c = king[1];
		while (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
			if (occupied[r][c]) {
				return Arrays.asList(r, c);
			}

			r += rOffset;
			c += cOffset;
		}

		return null;
	}
}
