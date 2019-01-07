import java.math.BigInteger;

public class Solution {
	public boolean isRationalEqual(String S, String T) {
		Rational rationalS = new Rational(S);
		Rational rationalT = new Rational(T);

		int precision = Math.max(rationalS.nonRepeatingPart.length(), rationalT.nonRepeatingPart.length())
				+ gcd(rationalS.repeatingPart.length(), rationalT.repeatingPart.length()) * 2;

		return new BigInteger(rationalS.getDigits(precision)).subtract(new BigInteger(rationalT.getDigits(precision)))
				.abs().compareTo(BigInteger.ONE) <= 0;
	}

	static int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
}

class Rational {
	String integerPart;
	String nonRepeatingPart = "";
	String repeatingPart = "0";

	Rational(String str) {
		int pointIndex = str.indexOf('.');
		if (pointIndex < 0) {
			integerPart = str;
		} else {
			integerPart = str.substring(0, pointIndex);

			int leftBracketIndex = str.indexOf('(');
			if (leftBracketIndex < 0) {
				nonRepeatingPart = str.substring(pointIndex + 1);
			} else {
				nonRepeatingPart = str.substring(pointIndex + 1, leftBracketIndex);
				repeatingPart = str.substring(leftBracketIndex + 1, str.length() - 1);
			}
		}
	}

	String getDigits(int precision) {
		StringBuilder fraction = new StringBuilder(nonRepeatingPart);
		int repeatingIndex = 0;
		while (fraction.length() < precision) {
			fraction.append(repeatingPart.charAt(repeatingIndex));

			repeatingIndex = (repeatingIndex + 1) % repeatingPart.length();
		}
		return integerPart + fraction;
	}
}