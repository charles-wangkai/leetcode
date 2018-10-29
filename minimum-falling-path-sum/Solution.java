import java.util.Arrays;

public class Solution {
	public int minFallingPathSum(int[][] A) {
		int size = A.length;

		int[] minSums = Arrays.copyOf(A[0], size);
		for (int r = 1; r < size; r++) {
			int[] nextMinSums = new int[size];
			for (int c = 0; c < size; c++) {
				nextMinSums[c] = Integer.MAX_VALUE;
				for (int offset = -1; offset <= 1; offset++) {
					int prevC = c + offset;
					if (prevC >= 0 && prevC < size) {
						nextMinSums[c] = Math.min(nextMinSums[c], minSums[prevC]);
					}
				}

				nextMinSums[c] += A[r][c];
			}

			minSums = nextMinSums;
		}

		return Arrays.stream(minSums).min().getAsInt();
	}
}
