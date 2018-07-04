import java.util.ArrayList;
import java.util.List;

public class Solution {
	int result;

	public int reversePairs(int[] nums) {
		result = 0;
		mergeSort(nums, 0, nums.length - 1);
		return result;
	}

	void mergeSort(int[] nums, int beginIndex, int endIndex) {
		if (endIndex <= beginIndex) {
			return;
		}

		int middleIndex = (beginIndex + endIndex) / 2;
		mergeSort(nums, beginIndex, middleIndex);
		mergeSort(nums, middleIndex + 1, endIndex);

		updateResult(nums, beginIndex, endIndex);

		List<Integer> merged = new ArrayList<Integer>();
		int leftIndex = beginIndex;
		int rightIndex = middleIndex + 1;
		while (leftIndex <= middleIndex || rightIndex <= endIndex) {
			if (leftIndex == middleIndex + 1 || (rightIndex != endIndex + 1 && nums[rightIndex] <= nums[leftIndex])) {
				merged.add(nums[rightIndex]);
				rightIndex++;
			} else {
				merged.add(nums[leftIndex]);
				leftIndex++;
			}
		}

		for (int i = beginIndex; i <= endIndex; i++) {
			nums[i] = merged.get(i - beginIndex);
		}
	}

	void updateResult(int[] nums, int beginIndex, int endIndex) {
		int middleIndex = (beginIndex + endIndex) / 2;

		int leftIndex = beginIndex;
		for (int rightIndex = middleIndex + 1; rightIndex <= endIndex; rightIndex++) {
			while (leftIndex <= middleIndex && nums[leftIndex] <= 2L * nums[rightIndex]) {
				leftIndex++;
			}
			result += middleIndex + 1 - leftIndex;
		}
	}
}
