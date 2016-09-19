public class RemoveKDigits {
	public String removeKdigits(String num, int k) {
		StringBuilder sb = new StringBuilder();
		num += "0";

		int[] digitCounts = new int[10];
		for (int i = 0; i < k + 1; i++) {
			digitCounts[num.charAt(i) - '0']++;
		}

		int end = k;
		for (int i = 0; i < num.length(); i++) {
			int digit = num.charAt(i) - '0';

			int minDigit = findMinDigit(digitCounts);
			if (k == 0 || digit == minDigit) {
				sb.append(digit);

				if (end + 1 < num.length()) {
					end++;
					digitCounts[num.charAt(end) - '0']++;
				}
			} else {
				k--;
			}

			digitCounts[digit]--;
		}

		String result = removeLeadingZeros(sb.substring(0, sb.length() - 1));
		return result.isEmpty() ? "0" : result;
	}

	String removeLeadingZeros(String s) {
		int beginIndex = 0;
		while (beginIndex < s.length() && s.charAt(beginIndex) == '0') {
			beginIndex++;
		}
		return s.substring(beginIndex);
	}

	int findMinDigit(int[] digitCounts) {
		for (int i = 0;; i++) {
			if (digitCounts[i] != 0) {
				return i;
			}
		}
	}
}
