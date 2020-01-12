public class Solution {
	public int[] getNoZeroIntegers(int n) {
		for (int a = 1;; ++a) {
			int b = n - a;

			if (isNoZero(a) && isNoZero(b)) {
				return new int[] { a, b };
			}
		}
	}

	boolean isNoZero(int x) {
		return String.valueOf(x).chars().allMatch(ch -> ch != '0');
	}
}
