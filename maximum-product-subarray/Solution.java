class Solution {
	public int maxProduct(int[] nums) {
		int result = Integer.MIN_VALUE;
		long product = 0;
		long frontNegative = 1;
		long backNegative = 0;
		int size = 0;

		for (int i = 0; i <= nums.length; ++i) {
			if (i < nums.length) {
				result = Math.max(result, nums[i]);
			}

			if (i == nums.length || nums[i] == 0) {
				if (product > 0) {
					result = Math.max(result, (int) product);
				} else if (product < 0 && size >= 2) {
					result = Math.max(result, (int) (product / Math.max(frontNegative, backNegative)));
				}

				product = 0;
				frontNegative = 1;
				backNegative = 0;
				size = 0;
			} else {
				if (product == 0) {
					product = 1;
				}
				product *= nums[i];
				if (nums[i] > 0) {
					if (frontNegative > 0) {
						frontNegative *= nums[i];
					}
					if (backNegative < 0) {
						backNegative *= nums[i];
					}
				} else {
					if (frontNegative > 0) {
						frontNegative *= nums[i];
					}
					backNegative = nums[i];
				}
				++size;
			}
		}

		return result;
	}
}
