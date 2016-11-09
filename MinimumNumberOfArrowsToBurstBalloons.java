import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons {
	public int findMinArrowShots(int[][] points) {
		if (points.length == 0) {
			return 0;
		}

		Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);

		int arrowNum = 1;
		int begin = points[0][0];
		int end = points[0][1];
		for (int i = 1; i < points.length; i++) {
			if (points[i][0] > end) {
				arrowNum++;

				begin = points[i][0];
				end = points[i][1];
			} else {
				begin = Math.max(begin, points[i][0]);
				end = Math.min(end, points[i][1]);
			}
		}
		return arrowNum;
	}
}
