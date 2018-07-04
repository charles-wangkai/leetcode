public class Solution {
	public boolean isUgly(int num) {
		if (num <= 0) {
			return false;
		}

		for (int factor : new int[] { 2, 3, 5 }) {
			while (num % factor == 0) {
				num /= factor;
			}
		}
		return num == 1;
	}
}
