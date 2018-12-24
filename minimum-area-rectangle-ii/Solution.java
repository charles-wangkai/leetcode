public class Solution {
	public double minAreaFreeRect(int[][] points) {
		boolean found = false;
		double minArea = Double.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if (j == i) {
					continue;
				}

				for (int k = 0; k < points.length; k++) {
					if (k == i || k == j || !isRightAngle(points[i], points[j], points[k])) {
						continue;
					}

					for (int p = 0; p < points.length; p++) {
						if (p == i || p == j || p == k || !isRightAngle(points[j], points[k], points[p])
								|| !isRightAngle(points[k], points[p], points[i])) {
							continue;
						}

						found = true;
						minArea = Math.min(minArea,
								computeDistance(points[i], points[j]) * computeDistance(points[j], points[k]));
					}
				}
			}
		}
		return found ? minArea : 0;
	}

	boolean isRightAngle(int[] p1, int[] p2, int[] p3) {
		return (long) (p1[0] - p2[0]) * (p3[0] - p2[0]) + (p1[1] - p2[1]) * (p3[1] - p2[1]) == 0;
	}

	double computeDistance(int[] p1, int[] p2) {
		return Math.sqrt((long) (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
	}
}
