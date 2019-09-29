import java.util.stream.IntStream;

public class Solution {
	public int equalSubstring(String s, String t, int maxCost) {
		int n = s.length();
		int[] costs = IntStream.range(0, n).map(i -> Math.abs(s.charAt(i) - t.charAt(i))).toArray();

		int result = 0;
		int beginIndex = 0;
		int sum = 0;
		for (int endIndex = 0; endIndex < n; endIndex++) {
			sum += costs[endIndex];

			while (sum > maxCost) {
				sum -= costs[beginIndex];
				beginIndex++;
			}

			result = Math.max(result, endIndex - beginIndex + 1);
		}

		return result;
	}
}
