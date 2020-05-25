public class Solution {
	public int maxUncrossedLines(int[] A, int[] B) {
		int[][] lineNums = new int[A.length + 1][B.length + 1];
		for (int i = 1; i <= A.length; ++i) {
			for (int j = 1; j <= B.length; ++j) {
				if (A[i - 1] == B[j - 1]) {
					lineNums[i][j] = lineNums[i - 1][j - 1] + 1;
				} else {
					lineNums[i][j] = Math.max(lineNums[i - 1][j], lineNums[i][j - 1]);
				}
			}
		}

		return lineNums[A.length][B.length];
	}
}
