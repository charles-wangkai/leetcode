public class NumberOf1Bits {
	public int hammingWeight(int n) {
		long number = (long) n;
		if (number < 0) {
			number += 1L << 32;
		}

		int oneCount = 0;
		while (number != 0) {
			number = number & (number - 1);
			oneCount++;
		}
		return oneCount;
	}
}
