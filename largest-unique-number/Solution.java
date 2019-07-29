import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int largestUniqueNumber(int[] A) {
		Map<Integer, Integer> valueToCount = new HashMap<>();
		for (int value : A) {
			valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
		}

		return valueToCount.keySet().stream().filter(value -> valueToCount.get(value) == 1).mapToInt(x -> x).max()
				.orElse(-1);
	}
}
