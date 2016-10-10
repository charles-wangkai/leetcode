public class ArithmeticSlices {
	public int numberOfArithmeticSlices(int[] A) {
		int prevDiff = 0;
		int count = 0;
		int result = 0;
		for (int i = 1; i < A.length; i++) {
			int diff = A[i] - A[i - 1];
			if (diff == prevDiff) {
				count++;
			} else {
				result += C(count, 2);

				prevDiff = diff;
				count = 1;
			}
		}
		result += C(count, 2);

		return result;
	}

	int C(int n, int m) {
		if (n < m) {
			return 0;
		}

		int result = 1;
		for (int i = n; i > n - m; i--) {
			result *= i;
		}
		for (int i = 1; i <= m; i++) {
			result /= i;
		}
		return result;
	}
}
