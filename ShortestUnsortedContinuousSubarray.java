import java.util.Arrays;

public class ShortestUnsortedContinuousSubarray {
	public int findUnsortedSubarray(int[] nums) {
		int[] sorted = Arrays.copyOf(nums, nums.length);
		Arrays.sort(sorted);

		int beginIndex = 0;
		while (beginIndex < nums.length && nums[beginIndex] == sorted[beginIndex]) {
			beginIndex++;
		}

		if (beginIndex == nums.length) {
			return 0;
		}

		int endIndex = nums.length - 1;
		while (endIndex >= 0 && nums[endIndex] == sorted[endIndex]) {
			endIndex--;
		}

		return endIndex - beginIndex + 1;
	}
}
