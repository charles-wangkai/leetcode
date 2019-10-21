import java.util.Arrays;

public class Solution {
	public int maximizeSweetness(int[] sweetness, int K) {
		int result = -1;
		int lower = Arrays.stream(sweetness).min().getAsInt();
		int upper = Arrays.stream(sweetness).sum();
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (check(sweetness, K, middle)) {
				result = middle;

				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}

		return result;
	}

	boolean check(int[] sweetness, int K, int pieceMinSum) {
		int pieceCount = 0;
		int sum = 0;
		for (int s : sweetness) {
			sum += s;

			if (sum >= pieceMinSum) {
				pieceCount++;
				sum = 0;
			}
		}

		return pieceCount >= K + 1;
	}
}
