import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	static final Operation[] OPERATIONS = {
			(x, y) -> new Rational(x.numerator * y.denominator + y.numerator * x.denominator,
					x.denominator * y.denominator),
			(x, y) -> new Rational(x.numerator * y.denominator - y.numerator * x.denominator,
					x.denominator * y.denominator),
			(x, y) -> new Rational(x.numerator * y.numerator, x.denominator * y.denominator), (x, y) -> {
				if (y.numerator == 0) {
					throw new UnsupportedOperationException();
				}
				return new Rational(x.numerator * y.denominator, x.denominator * y.numerator);
			} };

	public boolean judgePoint24(int[] nums) {
		return search(Arrays.stream(nums).mapToObj(num -> new Rational(num, 1)).collect(Collectors.toList()));
	}

	boolean search(List<Rational> numbers) {
		if (numbers.size() == 1) {
			return numbers.get(0).equals(new Rational(24, 1));
		}

		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (j == i) {
					continue;
				}

				for (Operation operation : OPERATIONS) {
					List<Rational> nextNumbers = new ArrayList<Rational>();

					try {
						nextNumbers.add(operation.operate(numbers.get(i), numbers.get(j)));
					} catch (UnsupportedOperationException e) {
						continue;
					}

					int tempI = i;
					int tempJ = j;
					nextNumbers.addAll(IntStream.range(0, numbers.size()).filter(k -> k != tempI && k != tempJ)
							.mapToObj(k -> numbers.get(k)).collect(Collectors.toList()));

					if (search(nextNumbers)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

@FunctionalInterface
interface Operation {
	Rational operate(Rational x, Rational y);
}

class Rational {
	int numerator;
	int denominator;

	Rational(int numerator, int denominator) {
		int g = gcd(numerator, denominator);

		this.numerator = numerator / g;
		this.denominator = denominator / g;
	}

	@Override
	public boolean equals(Object obj) {
		Rational other = (Rational) obj;
		return numerator == other.numerator && denominator == other.denominator;
	}

	int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
}