import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Solution {
	public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
		Set<Point> lampPoints = new HashSet<>();
		for (int[] lamp : lamps) {
			lampPoints.add(new Point(lamp[0], lamp[1]));
		}

		Map<Integer, Integer> xToCount = new HashMap<>();
		Map<Integer, Integer> yToCount = new HashMap<>();
		Map<Integer, Integer> xySumToCount = new HashMap<>();
		Map<Integer, Integer> xyDiffToCount = new HashMap<>();
		for (Point lampPoint : lampPoints) {
			xToCount.put(lampPoint.x, xToCount.getOrDefault(lampPoint.x, 0) + 1);
			yToCount.put(lampPoint.y, yToCount.getOrDefault(lampPoint.y, 0) + 1);
			xySumToCount.put(lampPoint.x + lampPoint.y, xySumToCount.getOrDefault(lampPoint.x + lampPoint.y, 0) + 1);
			xyDiffToCount.put(lampPoint.x - lampPoint.y, xyDiffToCount.getOrDefault(lampPoint.x - lampPoint.y, 0) + 1);
		}

		int[] result = new int[queries.length];
		for (int i = 0; i < result.length; i++) {
			int[] query = queries[i];

			result[i] = (xToCount.getOrDefault(query[0], 0) > 0 || yToCount.getOrDefault(query[1], 0) > 0
					|| xySumToCount.getOrDefault(query[0] + query[1], 0) > 0
					|| xyDiffToCount.getOrDefault(query[0] - query[1], 0) > 0) ? 1 : 0;

			for (int xOffset = -1; xOffset <= 1; xOffset++) {
				for (int yOffset = -1; yOffset <= 1; yOffset++) {
					int adjX = query[0] + xOffset;
					int adjY = query[1] + yOffset;

					Point adjPoint = new Point(adjX, adjY);
					if (lampPoints.contains(adjPoint)) {
						lampPoints.remove(adjPoint);

						xToCount.put(adjPoint.x, xToCount.get(adjPoint.x) - 1);
						yToCount.put(adjPoint.y, yToCount.get(adjPoint.y) - 1);
						xySumToCount.put(adjPoint.x + adjPoint.y, xySumToCount.get(adjPoint.x + adjPoint.y) - 1);
						xyDiffToCount.put(adjPoint.x - adjPoint.y, xyDiffToCount.get(adjPoint.x - adjPoint.y) - 1);
					}
				}
			}
		}
		return result;
	}
}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}
}