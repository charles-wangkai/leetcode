import java.util.Arrays;

public class Solution {
	public boolean makesquare(int[] nums) {
		Arrays.sort(nums);

		long perimeter = Arrays.stream(nums).asLongStream().sum();

		if (perimeter % 4 != 0) {
			return false;
		}

		long size = perimeter / 4;
		return search(nums, size, new boolean[nums.length], 0, 0, nums.length - 1);
	}

	boolean search(int[] nums, long size, boolean[] used, int side, int length, int index) {
		if (side == 3) {
			return true;
		}
		if (index < 0) {
			return false;
		}

		if (search(nums, size, used, side, length, index - 1)) {
			return true;
		}

		if (!used[index] && length + nums[index] <= size) {
			int nextSide = side;
			int nextLength = length + nums[index];
			int nextIndex = index;
			if (nextLength == size) {
				nextSide++;
				nextLength = 0;
				nextIndex = nums.length - 1;
			}

			used[index] = true;
			if (search(nums, size, used, nextSide, nextLength, nextIndex)) {
				return true;
			}
			used[index] = false;
		}

		return false;
	}
}
