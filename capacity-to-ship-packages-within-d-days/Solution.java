import java.util.Arrays;

public class Solution {
	public int shipWithinDays(int[] weights, int D) {
		int lower = Arrays.stream(weights).max().getAsInt();
		int upper = Arrays.stream(weights).sum();

		int result = -1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (computeDay(weights, middle) <= D) {
				result = middle;

				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return result;
	}

	static int computeDay(int[] weights, int capacity) {
		int day = 0;
		int remain = 0;
		for (int weight : weights) {
			if (weight > remain) {
				day++;
				remain = capacity;
			}

			remain -= weight;
		}
		return day;
	}
}
