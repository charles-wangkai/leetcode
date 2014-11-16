public class PlusOne {
	public int[] plusOne(int[] digits) {
		int[] result;
		if (isAllNine(digits)) {
			result = new int[digits.length + 1];
			result[0] = 1;
		} else {
			result = new int[digits.length];
			int carry = 1;
			for (int i = result.length - 1; i >= 0; i--) {
				int sum = digits[i] + carry;
				result[i] = sum % 10;
				carry = sum / 10;
			}
		}
		return result;
	}

	boolean isAllNine(int[] digits) {
		for (int digit : digits) {
			if (digit != 9) {
				return false;
			}
		}
		return true;
	}
}
