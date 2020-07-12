public class Solution {
	public int reverseBits(int n) {
		int reversed = 0;
		for (int i = 0; i < 32; i++) {
			reversed <<= 1;
			if ((n & 1) != 0) {
				++reversed;
			}
			n >>= 1;
		}

		return reversed;
	}
}
