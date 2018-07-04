import java.util.HashMap;
import java.util.Map;

// Definition for a point.
class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
}

public class Solution {
	public int maxPoints(Point[] points) {
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			Point origin = points[i];
			int originNum = 1;
			Map<Slope, Integer> slope2count = new HashMap<Slope, Integer>();
			for (int j = 0; j < points.length; j++) {
				if (j == i) {
					continue;
				}
				if (points[j].x == origin.x && points[j].y == origin.y) {
					originNum++;
				} else {
					Slope slope = new Slope(points[j].x - origin.x, points[j].y
							- origin.y);
					if (!slope2count.containsKey(slope)) {
						slope2count.put(slope, 0);
					}
					slope2count.put(slope, slope2count.get(slope) + 1);
				}
			}

			int maxCount = 0;
			for (int count : slope2count.values()) {
				maxCount = Math.max(maxCount, count);
			}
			result = Math.max(result, originNum + maxCount);
		}
		return result;
	}
}

class Slope {
	int dx;
	int dy;

	Slope(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		normalize();
	}

	private void normalize() {
		int common = gcd(Math.abs(dx), Math.abs(dy));
		dx /= common;
		dy /= common;

		if (dx < 0) {
			dx = -dx;
			dy = -dy;
		}
	}

	private int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	@Override
	public int hashCode() {
		return dx * dy;
	}

	@Override
	public boolean equals(Object obj) {
		Slope other = (Slope) obj;
		return dx == other.dx && dy == other.dy;
	}
}