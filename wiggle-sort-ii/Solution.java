import java.util.Random;

public class Solution {
	Random random = new Random();

	public void wiggleSort(int[] nums) {
		if (nums.length < 2) {
			return;
		}

		int k = (nums.length + 1) / 2;
		partition(nums, 0, nums.length - 1, k);
		int median = nums[k - 1];

		interlace(nums);

		swapToLeft(nums, 0, median);

		swapToLeft(nums, 1, median);
		reverse(nums, 1);
	}

	void partition(int[] nums, int beginIndex, int endIndex, int k) {
		while (true) {
			swap(nums, randomChooseBetween(beginIndex, endIndex), endIndex);

			int pivot = nums[endIndex];
			int largerFirstIndex = endIndex;
			for (int i = beginIndex; i < largerFirstIndex;) {
				if (nums[i] <= pivot) {
					i++;
				} else {
					largerFirstIndex--;
					swap(nums, i, largerFirstIndex);
				}
			}
			swap(nums, largerFirstIndex, endIndex);

			int expectedIndex = beginIndex + k - 1;
			if (expectedIndex < largerFirstIndex) {
				endIndex = largerFirstIndex - 1;
			} else if (expectedIndex > largerFirstIndex) {
				k -= largerFirstIndex - beginIndex + 1;
				beginIndex = largerFirstIndex + 1;
			} else {
				break;
			}
		}
	}

	int randomChooseBetween(int minBound, int maxBound) {
		return random.nextInt(maxBound - minBound + 1) + minBound;
	}

	void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}

	void interlace(int[] nums) {
		int leftFirstIndex = 0;
		int rightFirstIndex = (nums.length + 1) / 2;
		boolean chooseLeft = true;
		for (int i = 0; i < nums.length; i++) {
			if (!chooseLeft) {
				swap(nums, leftFirstIndex, rightFirstIndex);
				rightFirstIndex++;
			}
			leftFirstIndex++;

			chooseLeft = !chooseLeft;
		}
	}

	void swapToLeft(int[] nums, int firstIndex, int target) {
		int targetAfterIndex = firstIndex;
		for (int i = firstIndex; i < nums.length; i += 2) {
			if (nums[i] == target) {
				swap(nums, targetAfterIndex, i);
				targetAfterIndex += 2;
			}
		}
	}

	void reverse(int[] nums, int firstIndex) {
		int lastIndex = firstIndex;
		while (lastIndex + 2 < nums.length) {
			lastIndex += 2;
		}

		for (int i = firstIndex, j = lastIndex; i < j; i += 2, j -= 2) {
			swap(nums, i, j);
		}
	}
}
