import java.util.ArrayList;
import java.util.List;

class Solution {
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			int original = Math.abs(nums[i]);

			if (nums[original - 1] < 0) {
				result.add(original);
			} else {
				nums[original - 1] *= -1;
			}
		}

		return result;
	}
}