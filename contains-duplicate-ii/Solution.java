import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> history = new HashSet<Integer>();
		int begin = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i - begin > k) {
				history.remove(nums[begin]);
				begin++;
			}
			if (history.contains(nums[i])) {
				return true;
			}
			history.add(nums[i]);
		}
		return false;
	}
}
