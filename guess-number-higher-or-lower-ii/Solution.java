public class Solution {
	private Integer[][] maxMoneys;

	public int getMoneyAmount(int n) {
		maxMoneys = new Integer[n + 1][n + 1];
		return search(1, n);
	}

	private int search(int begin, int end) {
		if (begin > end) {
			return 0;
		}
		if (maxMoneys[begin][end] != null) {
			return maxMoneys[begin][end];
		}

		int result;
		if (begin == end) {
			result = 0;
		} else {
			result = Integer.MAX_VALUE;
			for (int i = begin; i <= end; i++) {
				result = Math.min(result, i + Math.max(search(begin, i - 1), search(i + 1, end)));
			}
		}
		maxMoneys[begin][end] = result;
		return result;
	}
}
