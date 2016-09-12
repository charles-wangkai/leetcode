public class RotateFunction {
	public int maxRotateFunction(int[] A) {
		int n = A.length;

		int left = 0;
		int leftSum = 0;

		int right = 0;
		int rightSum = 0;
		for (int k = 0; k < n; k++) {
			right += k * A[k];
			rightSum += A[k];
		}

		int result = left + right;
		for (int i = 0; i < n - 1; i++) {
			left += leftSum;
			leftSum += A[n - 1 - i];

			right += rightSum - n * A[n - 1 - i];
			rightSum -= A[n - 1 - i];

			result = Math.max(result, left + right);
		}
		return result;
	}
}
