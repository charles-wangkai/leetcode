import java.math.BigInteger;

public class Solution {
	public String smallestGoodBase(String n) {
		long nLong = Long.parseLong(n);
		for (int length = 59;; length--) {
			long base = binarySearch(2, nLong, new BigInteger(n), length);

			if (base >= 0) {
				return String.valueOf(base);
			}
		}
	}

	long binarySearch(long lower, long upper, BigInteger target, int length) {
		while (lower <= upper) {
			long middle = lower + (upper - lower) / 2;

			int comp = f(middle, length).compareTo(target);
			if (comp == 0) {
				return middle;
			} else if (comp < 0) {
				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return -1;
	}

	BigInteger f(long base, int length) {
		BigInteger result = BigInteger.ZERO;
		for (int i = 0; i < length; i++) {
			result = result.multiply(BigInteger.valueOf(base)).add(BigInteger.ONE);
		}
		return result;
	}
}
