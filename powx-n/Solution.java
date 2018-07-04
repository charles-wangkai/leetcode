public class Solution {
	public double pow(double x, int n) {
		if (n < 0) {
			x = 1 / x;
			n = -n;
		}
		double result = 1;
		while (n != 0) {
			if (n % 2 != 0) {
				result *= x;
			}
			x *= x;
			n /= 2;
		}
		return result;
	}
}
