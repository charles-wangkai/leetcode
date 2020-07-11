import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		for (int code = 0; code < 1 << nums.length; ++code) {
			List<Integer> subset = new ArrayList<>();
			for (int i = 0; i < nums.length; ++i) {
				if ((code & (1 << i)) != 0) {
					subset.add(nums[i]);
				}
			}

			result.add(subset);
		}

		return result;
	}
}
