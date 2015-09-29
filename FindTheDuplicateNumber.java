import java.util.Arrays;

public class FindTheDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int lower = 1;
		int upper = nums.length - 1;
		while (lower < upper) {
			int middle = (lower + upper) / 2;
			if (count(nums, lower, middle) > middle - lower + 1) {
				upper = middle;
			} else {
				lower = middle + 1;
			}
		}
		return lower;
	}

	int count(int[] nums, int min, int max) {
		return (int) Arrays.stream(nums).filter(num -> num >= min && num <= max).count();
	}
}
