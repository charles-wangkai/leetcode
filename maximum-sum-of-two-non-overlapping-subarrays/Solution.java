public class Solution {
	public int maxSumTwoNoOverlap(int[] A, int L, int M) {
		int[] prefixSums = new int[A.length + 1];
		int prefixSum = 0;
		for (int i = 1; i < prefixSums.length; i++) {
			prefixSum += A[i - 1];
			prefixSums[i] = prefixSum;
		}

		int result = -1;
		for (int lBegin = 1; lBegin + L <= prefixSums.length; lBegin++) {
			for (int mBegin = 1; mBegin + M <= prefixSums.length; mBegin++) {
				if (lBegin + L <= mBegin || mBegin + M <= lBegin) {
					result = Math.max(result, (prefixSums[lBegin + L - 1] - prefixSums[lBegin - 1])
							+ (prefixSums[mBegin + M - 1] - prefixSums[mBegin - 1]));
				}
			}
		}
		return result;
	}
}
