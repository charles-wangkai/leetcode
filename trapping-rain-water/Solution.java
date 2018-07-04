public class Solution {
	public int trap(int[] A) {
		int[] maxLeft = new int[A.length];
		for (int i = 0; i < maxLeft.length; i++) {
			maxLeft[i] = Math.max(A[i], (i == 0) ? 0 : maxLeft[i - 1]);
		}

		int[] maxRight = new int[A.length];
		for (int i = maxRight.length - 1; i >= 0; i--) {
			maxRight[i] = Math.max(A[i], (i == maxRight.length - 1) ? 0
					: maxRight[i + 1]);
		}

		int water = 0;
		for (int i = 0; i < A.length; i++) {
			water += Math.min(maxLeft[i], maxRight[i]) - A[i];
		}
		return water;
	}
}
