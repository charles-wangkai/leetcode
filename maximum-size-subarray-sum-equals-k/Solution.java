import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int maxSubArrayLen(int[] nums, int k) {
		Map<Integer, Integer> leftSum2length = buildSum2length(nums);
		Map<Integer, Integer> rightSum2length = buildSum2length(reverse(nums));

		int sum = Arrays.stream(nums).sum();
		int maxLength = 0;
		for (int leftSum : leftSum2length.keySet()) {
			int rightSum = sum - k - leftSum;
			if (rightSum2length.containsKey(rightSum)) {
				maxLength = Math.max(maxLength,
						nums.length - leftSum2length.get(leftSum) - rightSum2length.get(rightSum));
			}
		}
		return maxLength;
	}

	int[] reverse(int[] nums) {
		int[] reversed = new int[nums.length];
		for (int i = 0; i < reversed.length; i++) {
			reversed[i] = nums[nums.length - 1 - i];
		}
		return reversed;
	}

	Map<Integer, Integer> buildSum2length(int[] nums) {
		Map<Integer, Integer> sum2length = new HashMap<Integer, Integer>();
		int length = 0;
		int sum = 0;
		sum2length.put(length, sum);
		for (int i = 0; i < nums.length; i++) {
			length++;
			sum += nums[i];

			if (!sum2length.containsKey(sum)) {
				sum2length.put(sum, length);
			}
		}
		return sum2length;
	}
}
