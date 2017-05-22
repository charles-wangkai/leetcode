public class FractionAdditionAndSubtraction {
	public String fractionAddition(String expression) {
		Rational result = new Rational(0, 1);

		int beginIndex = 0;
		for (int i = 1; i <= expression.length(); i++) {
			if (i == expression.length() || expression.charAt(i) == '+' || expression.charAt(i) == '-') {
				String term = expression.substring(beginIndex, i);

				int divisionIndex = term.indexOf('/');
				int numerator = Integer.parseInt(term.substring(0, divisionIndex));
				int denominator = Integer.parseInt(term.substring(divisionIndex + 1));

				result = Rational.add(result, new Rational(numerator, denominator));

				beginIndex = i;
			}
		}

		return String.format("%d/%d", result.numerator, result.denominator);
	}
}

class Rational {
	int numerator;
	int denominator;

	Rational(int numerator, int denominator) {
		int g = gcd(Math.abs(numerator), denominator);
		this.numerator = numerator / g;
		this.denominator = denominator / g;
	}

	int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	static Rational add(Rational r1, Rational r2) {
		return new Rational(r1.numerator * r2.denominator + r2.numerator * r1.denominator,
				r1.denominator * r2.denominator);
	}
}