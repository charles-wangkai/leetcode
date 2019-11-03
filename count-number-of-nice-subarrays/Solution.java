import java.util.stream.IntStream;

public class Solution {
	public int numberOfSubarrays(int[] nums, int k) {
		int[] oddIndices = IntStream.range(0, nums.length).filter(i -> nums[i] % 2 != 0).toArray();

		int result = 0;
		for (int i = 0; i + k <= oddIndices.length; i++) {
			result += computeDiff(nums.length, oddIndices, i - 1, i)
					* computeDiff(nums.length, oddIndices, i + k - 1, i + k);
		}

		return result;
	}

	int computeDiff(int length, int[] oddIndices, int leftIndex, int rightIndex) {
		return (rightIndex == oddIndices.length ? length : oddIndices[rightIndex])
				- (leftIndex == -1 ? -1 : oddIndices[leftIndex]);
	}
}
