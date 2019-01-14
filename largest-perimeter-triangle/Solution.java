import java.util.Arrays;

public class Solution {
	public int largestPerimeter(int[] A) {
		Arrays.sort(A);

		int result = 0;
		for (int i = 1; i < A.length - 1; i++) {
			int index = Arrays.binarySearch(A, i + 1, A.length, A[i - 1] + A[i] - 1);
			if (index < 0) {
				index = -1 - index - 1;
				if (index < i + 1) {
					continue;
				}
			}

			result = Math.max(result, A[i - 1] + A[i] + A[index]);
		}
		return result;
	}
}
