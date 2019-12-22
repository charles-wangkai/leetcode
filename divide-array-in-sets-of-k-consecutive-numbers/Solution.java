import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public boolean isPossibleDivide(int[] nums, int k) {
		SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
		for (int value : nums) {
			valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
		}

		while (!valueToCount.isEmpty()) {
			int minValue = valueToCount.firstKey();
			for (int value = minValue; value < minValue + k; value++) {
				if (!valueToCount.containsKey(value)) {
					return false;
				}

				valueToCount.put(value, valueToCount.get(value) - 1);
				valueToCount.remove(value, 0);
			}
		}

		return true;
	}
}
