public class Solution {
	public double largestTriangleArea(int[][] points) {
		double maxArea = -1;
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					maxArea = Math.max(maxArea, computeArea(computeDistance(points[i], points[j]),
							computeDistance(points[j], points[k]), computeDistance(points[k], points[i])));
				}
			}
		}
		return maxArea;
	}

	double computeDistance(int[] point1, int[] point2) {
		return Math.sqrt(square(point1[0] - point2[0]) + square(point1[1] - point2[1]));
	}

	int square(int x) {
		return x * x;
	}

	double computeArea(double a, double b, double c) {
		double p = (a + b + c) / 2;
		return Math.sqrt(Math.max(0, p * (p - a) * (p - b) * (p - c)));
	}
}
