public class NthDigit {
	public int findNthDigit(int n) {
		if (n <= 9) {
			return n;
		}

		n -= 10;
		int size = 90;
		int digitNum = 2;
		while (true) {
			if (n <= (long) size * digitNum) {
				int number = size / 9 + n / digitNum;

				int digit = n % digitNum;
				return number / pow10(digitNum - digit - 1) % 10;
			}

			n -= size * digitNum;
			size *= 10;
			digitNum++;
		}
	}

	int pow10(int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= 10;
		}
		return result;
	}
}
