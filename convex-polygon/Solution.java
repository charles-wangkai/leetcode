import java.util.List;

public class Solution {
	public boolean isConvex(List<List<Integer>> points) {
		int prevSign = 0;
		for (int i = 0; i < points.size(); i++) {
			int cp = computeCrossProduct(points.get((i + 1) % points.size()), points.get(i),
					points.get((i + 2) % points.size()));
			int sign = (int) Math.signum(cp);
			if (sign != 0) {
				if (prevSign != 0 && sign != prevSign) {
					return false;
				}

				prevSign = sign;
			}
		}
		return true;
	}

	int computeCrossProduct(List<Integer> p0, List<Integer> p1, List<Integer> p2) {
		int x1 = p1.get(0) - p0.get(0);
		int y1 = p1.get(1) - p0.get(1);
		int x2 = p2.get(0) - p0.get(0);
		int y2 = p2.get(1) - p0.get(1);

		return x1 * y2 - x2 * y1;
	}
}
