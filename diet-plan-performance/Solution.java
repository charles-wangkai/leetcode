import java.util.stream.IntStream;

public class Solution {
	public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
		int point = 0;
		int total = IntStream.range(0, k - 1).map(i -> calories[i]).sum();
		for (int endIndex = k - 1; endIndex < calories.length; endIndex++) {
			total += calories[endIndex];

			if (total < lower) {
				point--;
			} else if (total > upper) {
				point++;
			}

			total -= calories[endIndex - k + 1];
		}

		return point;
	}
}
