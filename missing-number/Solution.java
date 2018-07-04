public class Solution {
	public int missingNumber(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int number = getNumber(nums, i);
			if (number < nums.length) {
				mark(nums, number);
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (!isMarked(nums, i)) {
				return i;
			}
		}
		return nums.length;
	}

	int getNumber(int[] a, int index) {
		return isMarked(a, index) ? flip(a[index]) : a[index];
	}

	void mark(int[] a, int index) {
		a[index] = flip(a[index]);
	}

	boolean isMarked(int[] a, int index) {
		return a[index] < 0;
	}

	int flip(int number) {
		return -1 - number;
	}
}
