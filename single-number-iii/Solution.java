import java.util.Arrays;

class Solution {
	public int[] singleNumber(int[] nums) {
		int xor = Arrays.stream(nums).reduce((x, y) -> x ^ y).getAsInt();

		int diffPos = 0;
		while ((xor & (1 << diffPos)) == 0) {
			++diffPos;
		}

		return new int[] { singleNumberForOne(nums, diffPos, false), singleNumberForOne(nums, diffPos, true) };
	}

	int singleNumberForOne(int[] nums, int pos, boolean setBit) {
		return Arrays.stream(nums).filter(num -> ((num & (1 << pos)) != 0) == setBit).reduce((x, y) -> x ^ y)
				.getAsInt();
	}
}
