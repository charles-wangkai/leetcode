import java.util.HashMap;
import java.util.Map;

public class Solution {
	public boolean isPossible(int[] nums) {
		Map<Integer, Integer> num2available = new HashMap<Integer, Integer>();
		for (int num : nums) {
			num2available.put(num, num2available.getOrDefault(num, 0) + 1);
		}

		Map<Integer, Integer> num2appendable = new HashMap<Integer, Integer>();
		for (int num : nums) {
			if (num2available.get(num) == 0) {
				continue;
			}

			if (num2appendable.getOrDefault(num, 0) > 0) {
				num2appendable.put(num, num2appendable.get(num) - 1);
				num2appendable.put(num + 1, num2appendable.getOrDefault(num + 1, 0) + 1);
			} else if (num2available.getOrDefault(num + 1, 0) > 0 && num2available.getOrDefault(num + 2, 0) > 0) {
				num2available.put(num + 1, num2available.get(num + 1) - 1);
				num2available.put(num + 2, num2available.get(num + 2) - 1);
				num2appendable.put(num + 3, num2appendable.getOrDefault(num + 3, 0) + 1);
			} else {
				return false;
			}

			num2available.put(num, num2available.get(num) - 1);
		}
		return true;
	}
}
