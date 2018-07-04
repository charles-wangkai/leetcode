import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int numberOfBoomerangs(int[][] points) {
		int result = 0;
		int n = points.length;

		for (int i = 0; i < n; i++) {
			Map<Integer, Integer> distanceSquare2count = new HashMap<Integer, Integer>();
			for (int j = 0; j < n; j++) {
				if (j == i) {
					continue;
				}

				int distanceSquare = computeDistanceSquare(points[i], points[j]);
				if (!distanceSquare2count.containsKey(distanceSquare)) {
					distanceSquare2count.put(distanceSquare, 0);
				}
				distanceSquare2count.put(distanceSquare, distanceSquare2count.get(distanceSquare) + 1);
			}

			result += distanceSquare2count.values().stream().mapToInt(count -> count * (count - 1)).sum();
		}

		return result;
	}

	int computeDistanceSquare(int[] point1, int[] point2) {
		int dx = point1[0] - point2[0];
		int dy = point1[1] - point2[1];

		return dx * dx + dy * dy;
	}
}
