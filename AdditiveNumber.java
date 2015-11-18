import java.math.BigInteger;

public class AdditiveNumber {
	public boolean isAdditiveNumber(String num) {
		for (int length1 = 1; length1 * 2 < num.length(); length1++) {
			for (int length2 = 1; Math.max(length1, length2) <= num.length() - length1 - length2; length2++) {
				if (isValid(num, length1, length2)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isValid(String str, int length1, int length2) {
		String number1Str = str.substring(0, length1);
		if (hasLeadingZeros(number1Str)) {
			return false;
		}
		BigInteger number1 = new BigInteger(number1Str);

		String number2Str = str.substring(length1, length1 + length2);
		if (hasLeadingZeros(number2Str)) {
			return false;
		}
		BigInteger number2 = new BigInteger(number2Str);

		int beginIndex = length1 + length2;
		while (beginIndex != str.length()) {
			BigInteger nextNumber = number1.add(number2);
			String nextNumberStr = nextNumber.toString();

			if (!str.startsWith(nextNumberStr, beginIndex)) {
				return false;
			}

			beginIndex += nextNumberStr.length();
			number1 = number2;
			number2 = nextNumber;
		}
		return true;
	}

	boolean hasLeadingZeros(String str) {
		return str.length() > 1 && str.charAt(0) == '0';
	}
}
