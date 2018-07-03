public class Solution {
	final int MODULUS = 1000000007;

	public int numDecodings(String s) {
		int prev = 0;
		int current = 1;
		for (int i = 0; i < s.length(); i++) {
			int next = 0;

			next = addMod(next, multiplyMod(current, countWays(s.substring(i, i + 1))));

			if (i >= 1) {
				next = addMod(next, multiplyMod(prev, countWays(s.substring(i - 1, i + 1))));
			}

			prev = current;
			current = next;
		}
		return current;
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MODULUS);
	}

	int countWays(String part) {
		if (part.length() == 1) {
			char ch = part.charAt(0);

			if (ch == '*') {
				return 9;
			} else if (ch == '0') {
				return 0;
			} else {
				return 1;
			}
		} else {
			char ch1 = part.charAt(0);
			char ch2 = part.charAt(1);

			if (ch1 == '*') {
				if (ch2 == '*') {
					return 15;
				} else if (ch2 <= '6') {
					return 2;
				} else {
					return 1;
				}
			} else if (ch1 == '0') {
				return 0;
			} else if (ch1 == '1') {
				if (ch2 == '*') {
					return 9;
				} else {
					return 1;
				}
			} else if (ch1 == '2') {
				if (ch2 == '*') {
					return 6;
				} else if (ch2 <= '6') {
					return 1;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
	}
}
