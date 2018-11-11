import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution {
	public int minAreaRect(int[][] points) {
		Set<Point> pointSet = new HashSet<>();
		for (int[] point : points) {
			pointSet.add(new Point(point[0], point[1]));
		}

		int minArea = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i][0] != points[j][0] && points[i][1] != points[j][1]
						&& pointSet.contains(new Point(points[i][0], points[j][1]))
						&& pointSet.contains(new Point(points[j][0], points[i][1]))) {
					minArea = Math.min(minArea,
							Math.abs((points[i][0] - points[j][0]) * (points[i][1] - points[j][1])));
				}
			}
		}
		return (minArea == Integer.MAX_VALUE) ? 0 : minArea;
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