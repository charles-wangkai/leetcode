import java.util.Arrays;

public class Solution {
	public int smallestDivisor(int[] nums, int threshold) {
		int result = -1;
		int lower = 1;
		int upper = Arrays.stream(nums).max().getAsInt();

		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (check(nums, threshold, middle)) {
				result = middle;
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}

		return result;
	}

	boolean check(int[] nums, int threshold, int divisor) {
		return Arrays.stream(nums).map(x -> x / divisor + (x % divisor == 0 ? 0 : 1)).sum() <= threshold;
	}
}
