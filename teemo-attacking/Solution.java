class Solution {
	public int findPoisonedDuration(int[] timeSeries, int duration) {
		int result = 0;
		for (int i = 0; i < timeSeries.length; ++i) {
			if (i + 1 != timeSeries.length && timeSeries[i + 1] - timeSeries[i] < duration) {
				result += timeSeries[i + 1] - timeSeries[i];
			} else {
				result += duration;
			}
		}

		return result;
	}
}
