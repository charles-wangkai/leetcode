import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int findLHS(int[] nums) {
		Map<Integer, Integer> num2count = new HashMap<Integer, Integer>();
		for (int num : nums) {
			if (!num2count.containsKey(num)) {
				num2count.put(num, 0);
			}
			num2count.put(num, num2count.get(num) + 1);
		}

		int result = 0;
		for (int num : nums) {
			for (int offset : new int[] { -1, 1 }) {
				if (num2count.containsKey(num + offset)) {
					result = Math.max(result, num2count.get(num) + num2count.get(num + offset));
				}
			}
		}
		return result;
	}
}
