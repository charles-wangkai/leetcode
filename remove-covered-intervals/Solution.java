import java.util.Arrays;

public class Solution {
	public int removeCoveredIntervals(int[][] intervals) {
		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

		int result = 0;
		int right = -1;
		for (int[] interval : intervals) {
			if (interval[1] > right) {
				right = interval[1];
				result++;
			}
		}

		return result;
	}
}
