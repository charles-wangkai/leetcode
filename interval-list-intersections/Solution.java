import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> result = new ArrayList<>();
		int indexA = 0;
		int indexB = 0;
		while (indexA < A.length && indexB < B.length) {
			int[] intervalA = A[indexA];
			int[] intervalB = B[indexB];

			if (intervalA[1] <= intervalB[1]) {
				if (intervalB[0] <= intervalA[1]) {
					result.add(new int[] { Math.max(intervalA[0], intervalB[0]), intervalA[1] });

					B[indexB][0] = intervalA[1];
				}

				++indexA;
			} else {
				if (intervalA[0] <= intervalB[1]) {
					result.add(new int[] { Math.max(intervalA[0], intervalB[0]), intervalB[1] });

					A[indexA][0] = intervalB[1];
				}

				++indexB;
			}
		}

		return result.toArray(new int[0][]);
	}
}
