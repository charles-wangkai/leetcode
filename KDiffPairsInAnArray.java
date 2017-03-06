import java.util.HashMap;
import java.util.Map;

public class KDiffPairsInAnArray {
	public int findPairs(int[] nums, int k) {
		Map<Integer, Integer> num2count = new HashMap<Integer, Integer>();
		for (int num : nums) {
			num2count.put(num, num2count.getOrDefault(num, 0) + 1);
		}

		if (k < 0) {
			return 0;
		} else if (k == 0) {
			return (int) num2count.values().stream().filter(count -> count > 1).count();
		} else {
			return (int) num2count.keySet().stream().filter(num -> num2count.containsKey(num + k)).count();
		}
	}
}
