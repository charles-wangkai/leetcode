public class Solution {
	public int minimumMoves(int[] arr) {
		int n = arr.length;

		int[][] minMoveNums = new int[n][n];
		for (int length = 1; length <= n; length++) {
			for (int beginIndex = 0; beginIndex + length <= n; beginIndex++) {
				int endIndex = beginIndex + length - 1;

				if (beginIndex == endIndex) {
					minMoveNums[beginIndex][endIndex] = 1;
				} else {
					minMoveNums[beginIndex][endIndex] = Integer.MAX_VALUE;

					for (int i = beginIndex; i < endIndex; i++) {
						minMoveNums[beginIndex][endIndex] = Math.min(minMoveNums[beginIndex][endIndex],
								minMoveNums[beginIndex][i] + minMoveNums[i + 1][endIndex]);
					}

					if (arr[beginIndex] == arr[endIndex]) {
						minMoveNums[beginIndex][endIndex] = Math.min(minMoveNums[beginIndex][endIndex],
								(beginIndex + 1 == endIndex) ? 1 : minMoveNums[beginIndex + 1][endIndex - 1]);
					}
				}
			}
		}

		return minMoveNums[0][n - 1];
	}
}
