import java.util.Arrays;

public class Solution {
	public boolean canThreePartsEqualSum(int[] A) {
		int sum = Arrays.stream(A).sum();
		if (sum % 3 != 0) {
			return false;
		}

		int partSum = sum / 3;

		int endIndex1 = findEndIndex(A, partSum, 0);
		if (endIndex1 < 0) {
			return false;
		}

		int endIndex2 = findEndIndex(A, partSum, endIndex1 + 1);

		return endIndex2 >= 0 && endIndex2 < A.length - 1;
	}

	int findEndIndex(int[] A, int partSum, int beginIndex) {
		int sum = 0;
		for (int i = beginIndex; i < A.length; i++) {
			sum += A[i];

			if (sum == partSum) {
				return i;
			}
		}
		return -1;
	}
}
