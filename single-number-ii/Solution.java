public class Solution {
	public int singleNumber(int[] A) {
		int[] bits = new int[32];
		for (int number : A) {
			for (int i = bits.length - 1; i >= 0; i--) {
				if ((number & (1 << (bits.length - 1 - i))) != 0) {
					bits[i] = (bits[i] + 1) % 3;
				}
			}
		}

		int result = 0;
		for (int bit : bits) {
			result = result * 2 + bit;
		}
		return result;
	}
}
