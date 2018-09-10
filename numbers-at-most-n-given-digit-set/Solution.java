public class Solution {
	public int atMostNGivenDigitSet(String[] D, int N) {
		String strN = String.valueOf(N);

		int result = 0;

		for (int length = 1; length < strN.length(); length++) {
			result += pow(D.length, length);
		}

		boolean digitInSet = true;
		for (int i = 0; i < strN.length() && digitInSet; i++) {
			char digit = strN.charAt(i);

			digitInSet = false;
			for (String d : D) {
				char buildingDigit = d.charAt(0);

				if (buildingDigit < digit) {
					result += pow(D.length, strN.length() - i - 1);
				} else if (buildingDigit == digit) {
					digitInSet = true;
				}
			}
		}
		if (digitInSet) {
			result++;
		}

		return result;
	}

	static int pow(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
}
