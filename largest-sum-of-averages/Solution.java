public class Solution {
	public double largestSumOfAverages(int[] A, int K) {
		double[][] maxSums = new double[A.length][K + 1];
		for (int i = -1; i < A.length; i++) {
			int sum = 0;
			int count = 0;
			for (int j = i + 1; j < A.length; j++) {
				sum += A[j];
				count++;
				double average = (double) sum / count;

				if (i == -1) {
					maxSums[j][1] = average;
				} else {
					for (int k = 1; k < K; k++) {
						maxSums[j][k + 1] = Math.max(maxSums[j][k + 1], maxSums[i][k] + average);
					}
				}
			}
		}
		return maxSums[A.length - 1][K];
	}
}
