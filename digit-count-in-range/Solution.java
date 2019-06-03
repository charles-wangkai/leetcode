public class Solution {
	public int digitsCount(int d, int low, int high) {
		return computeNum(d, high) - computeNum(d, low - 1);
	}

	int computeNum(int d, int limit) {
		String s;
		if (limit == 0) {
			s = "";
		} else {
			s = String.valueOf(limit);
		}

		int result = (d == 0) ? 1 : 0;
		int placeValue = 1;
		for (int i = 0; i < s.length() - (d == 0 ? 1 : 0); i++) {
			int upper = limit / placeValue;

			if (d == 0) {
				result += Math.max(0, upper / 10 - 1) * placeValue;
			} else {
				result += upper / 10 * placeValue;
			}

			if (upper % 10 > d) {
				result += placeValue;
			} else if (upper % 10 == d) {
				result += limit % placeValue + 1;
			}

			placeValue *= 10;
		}
		return result;
	}
}
