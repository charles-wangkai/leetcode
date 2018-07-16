public class Solution {
	public boolean reorderedPowerOf2(int N) {
		return search(String.valueOf(N).toCharArray(), 0);
	}

	boolean search(char[] digits, int index) {
		if (index == digits.length) {
			return isPowerOf2(Integer.parseInt(new String(digits)));
		}

		for (int i = index; i < digits.length; i++) {
			if (index == 0 && digits[i] == '0') {
				continue;
			}

			swap(digits, index, i);
			if (search(digits, index + 1)) {
				return true;
			}
			swap(digits, index, i);
		}

		return false;
	}

	boolean isPowerOf2(int x) {
		return (x & (x - 1)) == 0;
	}

	void swap(char[] digits, int index1, int index2) {
		char temp = digits[index1];
		digits[index1] = digits[index2];
		digits[index2] = temp;
	}
}
