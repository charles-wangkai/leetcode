public class SubarraySumEqualsK {
	public int subarraySum(int[] nums, int k) {
		int[] sums = new int[nums.length];
		int sum = 0;
		for (int i = 0; i < sums.length; i++) {
			sum += nums[i];
			sums[i] = sum;
		}

		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			if (sums[i] == k) {
				result++;
			}
			for (int j = 0; j < i; j++) {
				if (sums[i] - sums[j] == k) {
					result++;
				}
			}
		}
		return result;
	}
}
