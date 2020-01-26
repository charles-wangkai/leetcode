import java.util.Arrays;

public class Solution {
	public int minDifficulty(int[] jobDifficulty, int d) {
		if (jobDifficulty.length < d) {
			return -1;
		}

		int[][] minDifficultySums = new int[jobDifficulty.length + 1][d + 1];
		for (int i = 0; i < minDifficultySums.length; ++i) {
			Arrays.fill(minDifficultySums[i], Integer.MAX_VALUE);
		}
		minDifficultySums[0][0] = 0;

		for (int length = 1; length < minDifficultySums.length; ++length) {
			int maxDifficulty = -1;
			for (int i = length - 1; i >= 0; --i) {
				maxDifficulty = Math.max(maxDifficulty, jobDifficulty[i]);

				for (int day = 1; day <= d; ++day) {
					if (minDifficultySums[i][day - 1] != Integer.MAX_VALUE) {
						minDifficultySums[length][day] = Math.min(minDifficultySums[length][day],
								minDifficultySums[i][day - 1] + maxDifficulty);
					}
				}
			}
		}

		return minDifficultySums[jobDifficulty.length][d];
	}
}
