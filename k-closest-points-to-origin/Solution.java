import java.util.Arrays;

public class Solution {
	public int[][] kClosest(int[][] points, int K) {
		return Arrays.stream(points).sorted(
				(point1, point2) -> Integer.compare(computeDistanceSquare(point1), computeDistanceSquare(point2)))
				.limit(K).toArray(int[][]::new);
	}

	int computeDistanceSquare(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}
}
