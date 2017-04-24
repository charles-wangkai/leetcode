public class FindTheClosestPalindrome {
	public String nearestPalindromic(String n) {
		long minDiff = Long.MAX_VALUE;
		long result = -1;

		for (Long candidate : new Long[] { buildLessLength(n), buildMoreLength(n), buildFromHalf(n, -1),
				buildFromHalf(n, 0), buildFromHalf(n, 1) }) {
			if (candidate == null) {
				continue;
			}

			long diff = Math.abs(candidate - Long.parseLong(n));
			if (diff != 0 && (diff < minDiff || (diff == minDiff && candidate < result))) {
				minDiff = diff;
				result = candidate;
			}
		}
		return String.valueOf(result);
	}

	Long buildFromHalf(String n, int delta) {
		boolean oddLength = n.length() % 2 != 0;
		String half = n.substring(0, (n.length() + 1) / 2);

		String lowerHalf = String.valueOf(Long.parseLong(half) + delta);
		if (lowerHalf.length() != half.length()) {
			return null;
		}

		return mirror(lowerHalf, oddLength);
	}

	long mirror(String half, boolean oddLength) {
		String result = half;
		for (int i = half.length() - (oddLength ? 2 : 1); i >= 0; i--) {
			result += half.charAt(i);
		}
		return Long.parseLong(result);
	}

	long buildLessLength(String n) {
		if (n.length() == 1) {
			return Long.parseLong(n);
		}
		return Long.parseLong(repeat('9', n.length() - 1));
	}

	long buildMoreLength(String n) {
		return Long.parseLong('1' + repeat('0', n.length() - 1) + '1');
	}

	String repeat(char digit, int count) {
		String result = "";
		for (int i = 0; i < count; i++) {
			result += digit;
		}
		return result;
	}
}
