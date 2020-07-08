import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		Map<Integer, Integer> valueToCount = new HashMap<>();
		for (int value : nums) {
			valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
		}

		List<List<Integer>> result = new ArrayList<>();
		for (int a : valueToCount.keySet()) {
			valueToCount.put(a, valueToCount.get(a) - 1);

			for (int b : valueToCount.keySet()) {
				if (b >= a && valueToCount.get(b) != 0) {
					valueToCount.put(b, valueToCount.get(b) - 1);

					int c = -a - b;
					if (c >= b && valueToCount.getOrDefault(c, 0) != 0) {
						result.add(Arrays.asList(a, b, c));
					}

					valueToCount.put(b, valueToCount.get(b) + 1);
				}
			}

			valueToCount.put(a, valueToCount.get(a) + 1);
		}

		return result;
	}
}
