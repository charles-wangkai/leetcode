import java.util.HashMap;
import java.util.Map;

public class Solution {
	public boolean canDivideIntoSubsequences(int[] nums, int K) {
		Map<Integer, Integer> valueToCount = new HashMap<>();
		for (int num : nums) {
			valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
		}

		return valueToCount.values().stream().mapToInt(x -> x).max().getAsInt() * K <= nums.length;
	}
}
