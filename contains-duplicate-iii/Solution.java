import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (t < 0) {
			return false;
		}

		NavigableSet<Long> history = new TreeSet<>();
		int beginIndex = 0;
		for (int i = 0; i < nums.length; ++i) {
			if (i - beginIndex > k) {
				history.remove((long) nums[beginIndex]);
				++beginIndex;
			}

			if (!history.subSet((long) nums[i] - t, true, (long) nums[i] + t, true).isEmpty()) {
				return true;
			}

			history.add((long) nums[i]);
		}

		return false;
	}
}
