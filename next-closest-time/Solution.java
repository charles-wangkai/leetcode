public class Solution {
	static final int MINUTE_IN_DAY = 24 * 60;

	public String nextClosestTime(String time) {
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(3));

		int[] digits = { hour / 10, hour % 10, minute / 10, minute % 10 };

		int minDiff = Integer.MAX_VALUE;
		String result = null;
		for (int d1 : digits) {
			for (int d2 : digits) {
				for (int d3 : digits) {
					for (int d4 : digits) {
						int currentHour = d1 * 10 + d2;
						int currentMinute = d3 * 10 + d4;

						if (currentHour >= 24 || currentMinute >= 60) {
							continue;
						}

						int diff = computeDiff(currentHour, currentMinute, hour, minute);
						if (diff < minDiff) {
							minDiff = diff;
							result = String.format("%02d:%02d", currentHour, currentMinute);
						}
					}
				}
			}
		}
		return result;
	}

	int computeDiff(int currentHour, int currentMinute, int hour, int minute) {
		int currentTotalMinute = currentHour * 60 + currentMinute;
		int totalMinute = hour * 60 + minute;
		int diff = (currentTotalMinute - totalMinute + MINUTE_IN_DAY) % MINUTE_IN_DAY;
		if (diff == 0) {
			diff = MINUTE_IN_DAY;
		}
		return diff;
	}
}
