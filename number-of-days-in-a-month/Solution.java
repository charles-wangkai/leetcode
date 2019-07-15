public class Solution {
	static final int[] DAYS = { -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public int numberOfDays(int Y, int M) {
		return DAYS[M] + ((M == 2 && isLeapYear(Y)) ? 1 : 0);
	}

	boolean isLeapYear(int Y) {
		return Y % 400 == 0 || (Y % 100 != 0 && Y % 4 == 0);
	}
}
