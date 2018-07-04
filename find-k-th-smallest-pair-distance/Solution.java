import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);

		int lower = IntStream.range(0, nums.length - 1).map(i -> nums[i + 1] - nums[i]).min().getAsInt();
		int upper = nums[nums.length - 1] - nums[0];
		int result = -1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (computePairNum(nums, middle) < k) {
				result = middle;
				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return result;
	}

	int computePairNum(int[] nums, int distance) {
		int pairNum = 0;
		int endIndex = 0;
		for (int i = 0; i < nums.length; i++) {
			while (endIndex + 1 < nums.length && nums[endIndex + 1] - nums[i] < distance) {
				endIndex++;
			}
			pairNum += endIndex - i;
		}
		return pairNum;
	}
}
