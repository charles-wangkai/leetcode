import java.util.Arrays;

public class Solution {
	public int[][] kClosest(int[][] points, int K) {
		return Arrays.stream(points)
				.sorted((p1, p2) -> Integer.compare(computeDistanceSquare(p1), computeDistanceSquare(p2))).limit(K)
				.toArray(int[][]::new);
	}

	int computeDistanceSquare(int[] point) {
		return point[0] * point[0] + point[1] * point[1];
	}
}
