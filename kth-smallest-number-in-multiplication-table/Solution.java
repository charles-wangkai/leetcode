public class Solution {
	public int findKthNumber(int m, int n, int k) {
		int lower = 1;
		int upper = m * n;
		int result = -1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			int notGreaterNum = countNotGreaterNum(m, n, middle);
			if (notGreaterNum >= k) {
				result = middle;

				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return result;
	}

	int countNotGreaterNum(int m, int n, int value) {
		int notGreaterNum = 0;
		for (int i = 1; i <= m; i++) {
			notGreaterNum += Math.min(n, value / i);
		}
		return notGreaterNum;
	}
}
