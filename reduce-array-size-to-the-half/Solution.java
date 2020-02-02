import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public int minSetSize(int[] arr) {
		Map<Integer, Integer> valueToCount = new HashMap<>();
		for (int value : arr) {
			valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
		}

		List<Integer> counts = valueToCount.values().stream().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());

		int sum = 0;
		for (int i = 0;; ++i) {
			sum += counts.get(i);

			if (sum * 2 >= arr.length) {
				return i + 1;
			}
		}
	}
}
