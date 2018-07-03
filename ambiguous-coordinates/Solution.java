import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> ambiguousCoordinates(String S) {
		String digits = S.substring(1, S.length() - 1);
		List<String> result = new ArrayList<String>();
		for (int leftLength = 1; leftLength < digits.length(); leftLength++) {
			String left = digits.substring(0, leftLength);
			String right = digits.substring(leftLength);

			List<String> leftNumbers = findNumbers(left);
			List<String> rightNumbers = findNumbers(right);
			for (String leftNumber : leftNumbers) {
				for (String rightNumber : rightNumbers) {
					result.add(String.format("(%s, %s)", leftNumber, rightNumber));
				}
			}
		}
		return result;
	}

	List<String> findNumbers(String s) {
		List<String> numbers = new ArrayList<String>();
		if (check(s)) {
			numbers.add(s);
		}
		for (int integerLength = 1; integerLength < s.length(); integerLength++) {
			String number = s.substring(0, integerLength) + "." + s.substring(integerLength);
			if (check(number)) {
				numbers.add(number);
			}
		}
		return numbers;
	}

	boolean check(String number) {
		int pointIndex = number.indexOf('.');
		if (pointIndex == -1) {
			return !number.startsWith("0") || number.length() == 1;
		} else {
			return pointIndex != 0 && pointIndex != number.length() - 1 && (!number.startsWith("0") || pointIndex == 1)
					&& !number.endsWith("0");
		}
	}
}
