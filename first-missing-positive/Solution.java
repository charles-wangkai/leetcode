class Solution {
	public int firstMissingPositive(int[] A) {
		for (int i = 0; i < A.length; ++i) {
			if (A[i] <= 0) {
				A[i] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < A.length; ++i) {
			int number = Math.abs(A[i]);
			if (number <= A.length) {
				A[number - 1] = -Math.abs(A[number - 1]);
			}
		}
		for (int i = 0;; ++i) {
			if (i == A.length || A[i] > 0) {
				return i + 1;
			}
		}
	}
}
