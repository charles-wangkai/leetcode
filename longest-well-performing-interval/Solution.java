import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int longestWPI(int[] hours) {
		Map<Integer, Integer> sumToMinIndex = new HashMap<>();
		int sum = 0;
		int result = 0;
		for (int i = 0; i < hours.length; i++) {
			sum += (hours[i] > 8) ? 1 : -1;

			if (sum > 0) {
				result = i + 1;
			} else {
				sumToMinIndex.putIfAbsent(sum, i);

				if (sumToMinIndex.containsKey(sum - 1)) {
					result = Math.max(result, i - sumToMinIndex.get(sum - 1));
				}
			}
		}
		return result;
	}
}
