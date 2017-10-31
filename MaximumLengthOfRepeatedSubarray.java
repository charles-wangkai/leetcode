public class MaximumLengthOfRepeatedSubarray {
	public int findLength(int[] A, int[] B) {
		int result = 0;
		int[][] maxLengths = new int[A.length + 1][B.length + 1];
		for (int i = 0; i <= A.length; i++) {
			for (int j = 0; j <= B.length; j++) {
				if (i > 0 && j > 0 && A[i - 1] == B[j - 1]) {
					maxLengths[i][j] = maxLengths[i - 1][j - 1] + 1;
					result = Math.max(result, maxLengths[i][j]);
				}
			}
		}
		return result;
	}
}
