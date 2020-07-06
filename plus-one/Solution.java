import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
	public int[] plusOne(int[] digits) {
		if (Arrays.stream(digits).allMatch(digit -> digit == 9)) {
			return IntStream.range(0, digits.length + 1).map(i -> (i == 0) ? 1 : 0).toArray();
		}

		int[] result = new int[digits.length];
		int carry = 1;
		for (int i = result.length - 1; i >= 0; --i) {
			int sum = digits[i] + carry;
			result[i] = sum % 10;
			carry = sum / 10;
		}

		return result;
	}
}
