import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

public class ErectTheFence {
	public List<Point> outerTrees(Point[] points) {
		Point p0 = points[0];
		for (int i = 1; i < points.length; i++) {
			if (points[i].y < p0.y || (points[i].y == p0.y && points[i].x < p0.x)) {
				p0 = points[i];
			}
		}

		List<Point> remains = new ArrayList<Point>();
		for (Point point : points) {
			if (point != p0) {
				remains.add(point);
			}
		}
		final Point origin = p0;
		Collections.sort(remains, (p1, p2) -> {
			int x1 = p1.x - origin.x;
			int y1 = p1.y - origin.y;
			int x2 = p2.x - origin.x;
			int y2 = p2.y - origin.y;

			int delta = y1 * x2 - y2 * x1;

			return (delta == 0) ? ((x2 * x2 + y2 * y2) - (x1 * x1 + y1 * y1)) : delta;
		});

		Map<Point, List<Point>> point2shorts = new HashMap<Point, List<Point>>();

		for (int i = 1; i < remains.size(); i++) {
			Point p1 = remains.get(i - 1);
			Point p2 = remains.get(i);

			int x1 = p1.x - origin.x;
			int y1 = p1.y - origin.y;
			int x2 = p2.x - origin.x;
			int y2 = p2.y - origin.y;

			int delta = y1 * x2 - y2 * x1;

			if (delta == 0) {
				remains.remove(i);

				if (!point2shorts.containsKey(p1)) {
					point2shorts.put(p1, new ArrayList<Point>());
				}
				point2shorts.get(p1).add(p2);

				i--;
			}
		}

		List<Point> result = new ArrayList<Point>();
		result.add(p0);
		for (int i = 0; i < 2 && i < remains.size(); i++) {
			result.add(remains.get(i));
		}

		for (int i = 2; i < remains.size(); i++) {
			while (computeCrossProduct(result.get(result.size() - 2), result.get(result.size() - 1),
					remains.get(i)) < 0) {
				result.remove(result.size() - 1);
			}
			result.add(remains.get(i));
		}

		List<Point> additions = new ArrayList<Point>();
		if (result.size() >= 2) {
			Point p = result.get(1);
			if (point2shorts.containsKey(p)) {
				additions.addAll(point2shorts.get(p));
			}
		}
		if (result.size() >= 3) {
			Point p = result.get(result.size() - 1);
			if (point2shorts.containsKey(p)) {
				additions.addAll(point2shorts.get(p));
			}
		}
		result.addAll(additions);

		return result;
	}

	int computeCrossProduct(Point p0, Point p1, Point p2) {
		int x1 = p1.x - p0.x;
		int y1 = p1.y - p0.y;
		int x2 = p2.x - p0.x;
		int y2 = p2.y - p0.y;

		return x1 * y2 - x2 * y1;
	}
}
