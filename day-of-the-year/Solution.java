public class Solution {
	static final int[] MONTH_DAYS = { -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public int dayOfYear(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8));

		int result = day;
		for (int i = 1; i < month; i++) {
			if (i == 2 && isLeap(year)) {
				result++;
			}

			result += MONTH_DAYS[i];
		}

		return result;
	}

	static boolean isLeap(int year) {
		return year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
	}
}
