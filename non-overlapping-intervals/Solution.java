import java.util.Arrays;

public class Solution {
	public int eraseOverlapIntervals(int[][] intervals) {
		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[1], i2[1]));

		int result = 0;
		int endTime = Integer.MIN_VALUE;
		for (int[] interval : intervals) {
			if (interval[0] >= endTime) {
				endTime = interval[1];
			} else {
				++result;
			}
		}

		return result;
	}
}
