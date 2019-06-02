import java.util.Arrays;

public class Solution {
	public String minimizeError(String[] prices, int target) {
		RealNumber[] realNumbers = Arrays.stream(prices).map(RealNumber::new)
				.sorted((realNumber1, realNumber2) -> Integer.compare(realNumber1.decimal, realNumber2.decimal))
				.toArray(RealNumber[]::new);

		int minSum = Arrays.stream(realNumbers).mapToInt(realNumber -> realNumber.integer).sum();
		int floorNum = realNumbers.length - (target - minSum);

		if (floorNum < 0 || floorNum > realNumbers.length) {
			return "-1";
		}

		int roundingError = 0;
		for (int i = 0; i < realNumbers.length; i++) {
			if (i < floorNum) {
				roundingError += realNumbers[i].decimal;
			} else {
				if (realNumbers[i].decimal == 0) {
					return "-1";
				}

				roundingError += 1000 - realNumbers[i].decimal;
			}
		}

		return String.format("%d.%03d", roundingError / 1000, roundingError % 1000);
	}
}

class RealNumber {
	int integer;
	int decimal;

	RealNumber(String s) {
		String[] fields = s.split("\\.");

		integer = Integer.parseInt(fields[0]);
		decimal = Integer.parseInt(fields[1]);
	}
}