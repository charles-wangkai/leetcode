public class Solution {
	long product;
	long frontNegative;
	long backNegative;
	int size;

	public int maxProduct(int[] A) {
		int result = Integer.MIN_VALUE;

		reset();
		for (int i = 0; i <= A.length; i++) {
			if (i < A.length) {
				result = Math.max(result, A[i]);
			}
			if (i == A.length || A[i] == 0) {
				if (product > 0) {
					result = Math.max(result, (int) product);
				} else if (product < 0 && size > 1) {
					result = Math.max(result, (int) (product / Math.max(
							frontNegative, backNegative)));
				}
				reset();
			} else {
				if (product == 0) {
					product = 1;
				}
				product *= A[i];
				if (A[i] > 0) {
					if (frontNegative > 0) {
						frontNegative *= A[i];
					}
					if (backNegative < 0) {
						backNegative *= A[i];
					}
				} else {
					if (frontNegative > 0) {
						frontNegative *= A[i];
					}
					backNegative = A[i];
				}
				size++;
			}
		}
		return result;
	}

	void reset() {
		product = 0;
		frontNegative = 1;
		backNegative = 0;
		size = 0;
	}
}
