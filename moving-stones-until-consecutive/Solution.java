import java.util.stream.IntStream;

public class Solution {
	public int[] numMovesStones(int a, int b, int c) {
		int[] sorted = IntStream.of(a, b, c).sorted().toArray();

		int minMove;
		if (sorted[2] - sorted[0] == 2) {
			minMove = 0;
		} else if (sorted[2] - sorted[1] <= 2 || sorted[1] - sorted[0] <= 2) {
			minMove = 1;
		} else {
			minMove = 2;
		}

		int maxMove = sorted[2] - sorted[0] - 2;

		return new int[] { minMove, maxMove };
	}
}
