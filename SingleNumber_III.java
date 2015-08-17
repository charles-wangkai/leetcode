public class SingleNumber_III {
	public int[] singleNumber(int[] nums) {
		int xor = 0;
		for (int num : nums) {
			xor ^= num;
		}

		int diffPos = 0;
		while ((xor & (1 << diffPos)) == 0) {
			diffPos++;
		}

		return new int[] { singleNumberForOne(nums, diffPos, false),
				singleNumberForOne(nums, diffPos, true) };
	}

	int singleNumberForOne(int[] nums, int pos, boolean setBit) {
		int xor = 0;
		for (int num : nums) {
			if (((num & (1 << pos)) != 0) == setBit) {
				xor ^= num;
			}
		}
		return xor;
	}
}
