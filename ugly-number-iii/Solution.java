import java.math.BigInteger;

public class Solution {
	public int nthUglyNumber(int n, int a, int b, int c) {
		int result = -1;
		int lower = 1;
		int upper = 2_000_000_000;
		while (lower <= upper) {
			int middle = lower + (upper - lower) / 2;

			if (computeUglyNum(middle, a, b, c) >= n) {
				result = middle;

				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}

		return result;
	}

	static int computeUglyNum(int limit, int a, int b, int c) {
		return BigInteger.valueOf(limit).divide(BigInteger.valueOf(a))
				.add(BigInteger.valueOf(limit).divide(BigInteger.valueOf(b)))
				.add(BigInteger.valueOf(limit).divide(BigInteger.valueOf(c)))
				.subtract(BigInteger.valueOf(limit).divide(lcm(BigInteger.valueOf(a), BigInteger.valueOf(b))))
				.subtract(BigInteger.valueOf(limit).divide(lcm(BigInteger.valueOf(b), BigInteger.valueOf(c))))
				.subtract(BigInteger.valueOf(limit).divide(lcm(BigInteger.valueOf(c), BigInteger.valueOf(a))))
				.add((BigInteger.valueOf(limit)
						.divide(lcm(lcm(BigInteger.valueOf(a), BigInteger.valueOf(b)), BigInteger.valueOf(c)))))
				.intValue();
	}

	static BigInteger lcm(BigInteger x, BigInteger y) {
		return x.multiply(y).divide(x.gcd(y));
	}
}
