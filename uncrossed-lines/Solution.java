public class Solution {
	public int maxUncrossedLines(int[] A, int[] B) {
		int[][] maxLines = new int[A.length + 1][B.length + 1];
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i - 1] == B[j - 1]) {
					maxLines[i][j] = maxLines[i - 1][j - 1] + 1;
				} else {
					maxLines[i][j] = Math.max(maxLines[i - 1][j], maxLines[i][j - 1]);
				}
			}
		}
		return maxLines[A.length][B.length];
	}
}
