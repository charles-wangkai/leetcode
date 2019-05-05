import java.util.Arrays;

public class Solution {
	public int[] numMovesStonesII(int[] stones) {
		int n = stones.length;

		Arrays.sort(stones);

		int minMove = Integer.MAX_VALUE;
		int beginIndex = 0;
		for (int endIndex = 0; endIndex < n; endIndex++) {
			while (stones[endIndex] - stones[beginIndex] >= n) {
				beginIndex++;
			}

			if (endIndex - beginIndex + 1 == n - 1 && stones[endIndex] - stones[beginIndex] == n - 2) {
				minMove = Math.min(minMove, 2);
			} else {
				minMove = Math.min(minMove, n - (endIndex - beginIndex + 1));
			}
		}

		int maxMove = Math.max(stones[n - 1] - stones[1] - n + 2, stones[n - 2] - stones[0] - n + 2);

		return new int[] { minMove, maxMove };
	}
}
