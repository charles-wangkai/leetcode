public class Solution {
	public int partitionDisjoint(int[] A) {
		int leftMax = Integer.MIN_VALUE;
		int[] leftMaxs = new int[A.length];
		for (int i = 0; i < leftMaxs.length; i++) {
			leftMax = Math.max(leftMax, A[i]);
			leftMaxs[i] = leftMax;
		}

		int rightMin = Integer.MAX_VALUE;
		int[] rightMins = new int[A.length];
		for (int i = rightMins.length - 1; i >= 0; i--) {
			rightMin = Math.min(rightMin, A[i]);
			rightMins[i] = rightMin;
		}

		for (int i = 0;; i++) {
			if (leftMaxs[i] <= rightMins[i + 1]) {
				return i + 1;
			}
		}
	}
}
