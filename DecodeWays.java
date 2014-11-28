public class DecodeWays {
	public int numDecodings(String s) {
		if (s.isEmpty()) {
			return 0;
		}

		int prev = 0;
		int current = 1;
		for (int i = 0; i < s.length(); i++) {
			int next = 0;
			int oneDigit = Integer.parseInt(s.substring(i, i + 1));
			if (oneDigit >= 1 && oneDigit <= 9) {
				next += current;
			}
			int twoDigit = Integer.parseInt(s.substring(Math.max(i - 1, 0),
					i + 1));
			if (twoDigit >= 10 && twoDigit <= 26) {
				next += prev;
			}
			prev = current;
			current = next;
		}
		return current;
	}
}
