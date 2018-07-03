public class Solution {
	public int rangeBitwiseAnd(int m, int n) {
		int base = 1;
		int result = 0;
		while (n != 0) {
			if (m == n && (m & 1) != 0) {
				result += base;
			}

			m >>= 1;
			n >>= 1;
			base <<= 1;
		}
		return result;
	}
}
