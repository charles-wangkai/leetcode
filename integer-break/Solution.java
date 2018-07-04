public class Solution {
	public int integerBreak(int n) {
		if (n == 2) {
			return 1;
		} else if (n == 3) {
			return 2;
		}

		int num2 = 0;
		while ((n - num2 * 2) % 3 != 0) {
			num2++;
		}
		int num3 = (n - num2 * 2) / 3;
		return pow(2, num2) * pow(3, num3);
	}

	int pow(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
}
