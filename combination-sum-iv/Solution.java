import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int combinationSum4(int[] nums, int target) {
		int result = 0;

		Map<Integer, Integer> sum2count = new HashMap<Integer, Integer>();
		sum2count.put(0, 1);
		while (!sum2count.isEmpty()) {
			Map<Integer, Integer> nextSum2count = new HashMap<Integer, Integer>();

			for (int sum : sum2count.keySet()) {
				int count = sum2count.get(sum);
				for (int num : nums) {
					int nextSum = sum + num;
					if (nextSum < target) {
						if (!nextSum2count.containsKey(nextSum)) {
							nextSum2count.put(nextSum, 0);
						}
						nextSum2count.put(nextSum, nextSum2count.get(nextSum) + count);
					} else if (nextSum == target) {
						result += count;
					}
				}
			}

			sum2count = nextSum2count;
		}

		return result;
	}
}
