public class Solution {
	public String removeKdigits(String num, int k) {
		num += '0';

		int[] digitCounts = new int[10];
		for (int i = 0; i < k + 1; i++) {
			++digitCounts[num.charAt(i) - '0'];
		}

		StringBuilder result = new StringBuilder();
		int endIndex = k + 1;
		for (int i = 0; i < num.length() - 1; ++i) {
			int digit = num.charAt(i) - '0';
			int minDigit = findMinDigit(digitCounts);

			if (digit == minDigit) {
				if (digit != 0 || result.length() != 0) {
					result.append(digit);
				}

				if (endIndex < num.length()) {
					digitCounts[num.charAt(endIndex) - '0']++;
					++endIndex;
				}
			}

			--digitCounts[digit];
		}

		return (result.length() == 0) ? "0" : result.toString();
	}

	int findMinDigit(int[] digitCounts) {
		for (int i = 0;; ++i) {
			if (digitCounts[i] != 0) {
				return i;
			}
		}
	}
}
