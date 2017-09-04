public class MaximumSwap {
	public int maximumSwap(int num) {
		char[] digits = String.valueOf(num).toCharArray();
		int result = num;
		for (int i = 0; i < digits.length; i++) {
			for (int j = i + 1; j < digits.length; j++) {
				swap(digits, i, j);
				result = Math.max(result, Integer.parseInt(new String(digits)));
				swap(digits, i, j);
			}
		}
		return result;
	}

	void swap(char[] digits, int index1, int index2) {
		char temp = digits[index1];
		digits[index1] = digits[index2];
		digits[index2] = temp;
	}
}
