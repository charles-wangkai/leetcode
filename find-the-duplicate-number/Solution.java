import java.util.Arrays;

public class Solution {
	public int findDuplicate(int[] nums) {
		int lower = 1;
		int upper = nums.length - 1;
		while (lower < upper) {
			int middle = (lower + upper) / 2;

			int lower_ = lower;
			if (Arrays.stream(nums).filter(num -> num >= lower_ && num <= middle).count() > middle - lower + 1) {
				upper = middle;
			} else {
				lower = middle + 1;
			}
		}

		return lower;
	}
}
