public class Solution {
	public int thirdMax(int[] nums) {
		Integer first = null;
		Integer second = null;
		Integer third = null;

		for (int num : nums) {
			if (first == null || num > first) {
				third = second;
				second = first;
				first = num;
			} else if (num < first) {
				if (second == null | num > second) {
					third = second;
					second = num;
				} else if (num < second) {
					if (third == null || num > third) {
						third = num;
					}
				}
			}
		}

		return third == null ? first : third;
	}
}
