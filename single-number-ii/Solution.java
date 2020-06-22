import java.util.Arrays;

public class Solution {
	public int singleNumber(int[] nums) {
		int[] bits = new int[32];
		for (int num : nums) {
			for (int i = 0; i < bits.length; ++i) {
				if ((num & (1L << (bits.length - 1 - i))) != 0) {
					bits[i] = (bits[i] + 1) % 3;
				}
			}
		}

		return Arrays.stream(bits).reduce(0, (res, elem) -> res * 2 + elem);
	}
}
