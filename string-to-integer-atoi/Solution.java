public class Solution {
	public int atoi(String str) {
		str = str.trim();

		long number = 0;
		if (!str.isEmpty()) {
			char firstCh = str.charAt(0);
			boolean negative = firstCh == '-';
			int sign = negative ? -1 : 1;
			for (int i = (negative || firstCh == '+') ? 1 : 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (!Character.isDigit(ch)) {
					break;
				}
				number = number * 10 + (str.charAt(i) - '0') * sign;
				if (number > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				} else if (number < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
			}
		}
		return (int) number;
	}
}
