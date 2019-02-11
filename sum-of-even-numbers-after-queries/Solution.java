import java.util.Arrays;

public class Solution {
	public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
		int[] result = new int[queries.length];
		int sumEven = Arrays.stream(A).filter(this::isEven).sum();
		for (int i = 0; i < result.length; i++) {
			int val = queries[i][0];
			int index = queries[i][1];

			if (isEven(A[index])) {
				sumEven -= A[index];
			}
			A[index] += val;
			if (isEven(A[index])) {
				sumEven += A[index];
			}
			result[i] = sumEven;
		}
		return result;
	}

	boolean isEven(int x) {
		return x % 2 == 0;
	}
}
