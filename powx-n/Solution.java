class Solution {
	public double myPow(double x, int n) {
		long exponent = n;
		if (n < 0) {
			x = 1 / x;
			exponent *= -1;
		}

		double result = 1;
		while (exponent != 0) {
			if ((exponent & 1) != 0) {
				result *= x;
			}

			x *= x;
			exponent >>= 1;
		}

		return result;
	}
}
