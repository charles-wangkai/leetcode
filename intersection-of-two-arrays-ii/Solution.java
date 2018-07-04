import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> num2count1 = buildCounter(nums1);
		Map<Integer, Integer> num2count2 = buildCounter(nums2);

		List<Integer> merged = new ArrayList<Integer>();
		for (int num : num2count1.keySet()) {
			if (num2count2.containsKey(num)) {
				for (int i = 0; i < Math.min(num2count1.get(num), num2count2.get(num)); i++) {
					merged.add(num);
				}
			}
		}

		int[] result = new int[merged.size()];
		for (int i = 0; i < merged.size(); i++) {
			result[i] = merged.get(i);
		}
		return result;
	}

	Map<Integer, Integer> buildCounter(int[] nums) {
		Map<Integer, Integer> num2count = new HashMap<Integer, Integer>();
		for (int num : nums) {
			if (!num2count.containsKey(num)) {
				num2count.put(num, 0);
			}
			num2count.put(num, num2count.get(num) + 1);
		}
		return num2count;
	}
}
