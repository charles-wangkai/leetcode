import java.util.Arrays;

public class Solution {
	public int findBestValue(int[] arr, int target) {
		int minDiff = Integer.MAX_VALUE;
		int result = -1;
		int lower = 0;
		int upper = Arrays.stream(arr).max().getAsInt();

		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			int sum = Arrays.stream(arr).map(x -> Math.min(middle, x)).sum();
			int diff = Math.abs(sum - target);
			if (diff < minDiff || (diff == minDiff && middle < result)) {
				minDiff = diff;
				result = middle;
			}

			if (sum <= target) {
				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}

		return result;
	}
}
