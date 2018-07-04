public class Solution {
	public boolean isPerfectSquare(int num) {
		int lower = 1;
		int upper = 1 << 16;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			long square = (long) middle * middle;
			if (num == square) {
				return true;
			} else if (num < square) {
				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return false;
	}
}
