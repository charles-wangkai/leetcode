public class Solution {
	public int divide(int dividend, int divisor) {
		long a = dividend;
		long b = divisor;
		boolean positive = true;
		if (a < 0) {
			a = -a;
			positive = !positive;
		}
		if (b < 0) {
			b = -b;
			positive = !positive;
		}

		if (a < b) {
			return 0;
		}

		long result = 0;
		long power = b;
		long factor = 1;
		while (power << 1 <= a) {
			power <<= 1;
			factor <<= 1;
		}

		while (power >= b) {
			if (a >= power) {
				a -= power;
				result += factor;
			}
			power >>= 1;
			factor >>= 1;
		}

		if (!positive) {
			result = -result;
		}
		return (int) result;
	}
}
