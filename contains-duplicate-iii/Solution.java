import java.util.TreeSet;

public class Solution {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (t < 0) {
			return false;
		}

		TreeSet<Long> history = new TreeSet<Long>();
		int begin = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i - begin > k) {
				history.remove((long) nums[begin]);
				begin++;
			}
			if (!history.subSet((long) nums[i] - t, true, (long) nums[i] + t,
					true).isEmpty()) {
				return true;
			}
			history.add((long) nums[i]);
		}
		return false;
	}
}
