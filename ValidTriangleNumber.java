import java.util.Arrays;

public class ValidTriangleNumber {
	public int triangleNumber(int[] nums) {
		Arrays.sort(nums);

		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				result += Math.max(0, searchLimit(nums, nums[i] + nums[j]) - j);
			}
		}
		return result;
	}

	int searchLimit(int[] nums, int limit) {
		int lower = 0;
		int upper = nums.length - 1;
		int result = -1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (nums[middle] < limit) {
				result = middle;

				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return result;
	}
}
