import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
	public boolean isRectangleCover(int[][] rectangles) {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;

		Map<String, boolean[]> corner2directions = new HashMap<String, boolean[]>();

		int area = 0;
		for (int[] rectangle : rectangles) {
			int x0 = rectangle[0];
			int y0 = rectangle[1];
			int x1 = rectangle[2];
			int y1 = rectangle[3];

			area += (x1 - x0) * (y1 - y0);

			minX = Math.min(minX, x0);
			minY = Math.min(minY, y0);
			maxX = Math.max(maxX, x1);
			maxY = Math.max(maxY, y1);

			if (!updateCorner2directions(corner2directions, buildPoint(x0, y1), 0)) {
				return false;
			}
			if (!updateCorner2directions(corner2directions, buildPoint(x1, y1), 1)) {
				return false;
			}
			if (!updateCorner2directions(corner2directions, buildPoint(x1, y0), 2)) {
				return false;
			}
			if (!updateCorner2directions(corner2directions, buildPoint(x0, y0), 3)) {
				return false;
			}
		}

		if ((maxX - minX) * (maxY - minY) != area) {
			return false;
		}

		Set<String> boundary = new HashSet<String>();
		boundary.add(buildPoint(minX, maxY));
		boundary.add(buildPoint(maxX, maxY));
		boundary.add(buildPoint(maxX, minY));
		boundary.add(buildPoint(minX, minY));

		for (String boundaryCorner : boundary) {
			if (!corner2directions.containsKey(boundaryCorner)
					|| getDirectionNum(corner2directions.get(boundaryCorner)) != 1) {
				return false;
			}
		}

		for (String corner : corner2directions.keySet()) {
			if (boundary.contains(corner)) {
				continue;
			}

			if (getDirectionNum(corner2directions.get(corner)) == 1) {
				return false;
			}
		}

		return true;
	}

	String buildPoint(int x, int y) {
		return x + "," + y;
	}

	int getDirectionNum(boolean[] directions) {
		int directionNum = 0;
		for (boolean direction : directions) {
			if (direction) {
				directionNum++;
			}
		}
		return directionNum;
	}

	boolean updateCorner2directions(Map<String, boolean[]> corner2directions, String corner, int direction) {
		if (!corner2directions.containsKey(corner)) {
			corner2directions.put(corner, new boolean[4]);
		}

		if (corner2directions.get(corner)[direction]) {
			return false;
		}

		corner2directions.get(corner)[direction] = true;
		return true;
	}
}
