public class Solution {
	public boolean increasingTriplet(int[] nums) {
		Integer one = null;
		Integer first = null;
		Integer second = null;

		for (int num : nums) {
			if (second != null && num > second) {
				return true;
			}

			if (first != null && num > first && (second == null || num < second)) {
				second = num;
			}

			if (one != null && num > one && (first == null || second == null || (one <= first && num <= second))) {
				first = one;
				second = num;
			}

			if (one == null || num < one) {
				one = num;
			}
		}
		return false;
	}
}
