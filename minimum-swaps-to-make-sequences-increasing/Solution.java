public class Solution {
	public int minSwap(int[] A, int[] B) {
		int swapNum0 = 0;
		int swapNum1 = 1;
		for (int i = 1; i < A.length; i++) {
			int nextSwapNum0 = Integer.MAX_VALUE;
			int nextSwapNum1 = Integer.MAX_VALUE;

			if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
				nextSwapNum0 = Math.min(nextSwapNum0, swapNum0);

				if (swapNum1 != Integer.MAX_VALUE) {
					nextSwapNum1 = Math.min(nextSwapNum1, swapNum1 + 1);
				}
			}
			if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
				nextSwapNum0 = Math.min(nextSwapNum0, swapNum1);

				if (swapNum0 != Integer.MAX_VALUE) {
					nextSwapNum1 = Math.min(nextSwapNum1, swapNum0 + 1);
				}
			}

			swapNum0 = nextSwapNum0;
			swapNum1 = nextSwapNum1;
		}
		return Math.min(swapNum0, swapNum1);
	}
}
