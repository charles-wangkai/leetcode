public class Solution {
	public int maxSumAfterPartitioning(int[] A, int K) {
		int[] maxSums = new int[A.length];
		for (int i = 0; i < maxSums.length; i++) {
			int max = -1;
			for (int j = i; j >= 0 && j > i - K; j--) {
				max = Math.max(max, A[j]);
				maxSums[i] = Math.max(maxSums[i], (j == 0 ? 0 : maxSums[j - 1]) + max * (i - j + 1));
			}
		}
		return maxSums[maxSums.length - 1];
	}
}
