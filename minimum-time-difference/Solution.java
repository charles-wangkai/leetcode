import java.util.List;

public class Solution {
	public int findMinDifference(List<String> timePoints) {
		int[] minutes = timePoints.stream().mapToInt(this::toMinute).sorted().toArray();

		int minDifference = Integer.MAX_VALUE;
		for (int i = 0; i < minutes.length; i++) {
			int difference = (i == 0) ? (minutes[i] + 24 * 60 - minutes[minutes.length - 1])
					: (minutes[i] - minutes[i - 1]);
			minDifference = Math.min(minDifference, difference);
		}
		return minDifference;
	}

	int toMinute(String timePoint) {
		String[] fields = timePoint.split(":");
		int hour = Integer.parseInt(fields[0]);
		int minute = Integer.parseInt(fields[1]);
		return hour * 60 + minute;
	}
}
