public class Solution {
	public String toHex(int num) {
		if (num >= 0) {
			return hex(num);
		} else {
			return hex((1L << 32) + num);
		}
	}

	String hex(long n) {
		if (n == 0) {
			return "0";
		}

		String result = "";
		while (n != 0) {
			result = hexSymbol((int) (n % 16)) + result;
			n /= 16;
		}
		return result;
	}

	char hexSymbol(int digit) {
		if (digit <= 9) {
			return (char) ('0' + digit);
		} else {
			return (char) ('a' + digit - 10);
		}
	}
}
