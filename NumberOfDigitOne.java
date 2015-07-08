public class NumberOfDigitOne {
	public int countDigitOne(int n) {
		int count = 0;
		for (long base = 1; base <= n; base *= 10) {
			int front = n / (int) base;
			count += front / 10 * base;

			int lastDigitOfFront = front % 10;
			if (lastDigitOfFront > 1) {
				count += base;
			} else if (lastDigitOfFront == 1) {
				count += n % base + 1;
			}
		}
		return count;
	}
}
