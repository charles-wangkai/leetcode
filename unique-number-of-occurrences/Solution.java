import java.util.HashMap;
import java.util.Map;

public class Solution {
	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Integer> valueToCount = new HashMap<>();
		for (int value : arr) {
			valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
		}

		return valueToCount.values().stream().distinct().count() == valueToCount.size();
	}
}
