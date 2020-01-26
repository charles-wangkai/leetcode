import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
		int[][] distances = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				distances[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
			}
		}

		for (int[] edge : edges) {
			distances[edge[0]][edge[1]] = edge[2];
			distances[edge[1]][edge[0]] = edge[2];
		}

		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
						distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
					}
				}
			}
		}

		int minReachableCityNum = IntStream.range(0, n)
				.map(i -> (int) Arrays.stream(distances[i]).filter(distance -> distance <= distanceThreshold).count())
				.min().getAsInt();

		return IntStream.range(0, n).filter(i -> Arrays.stream(distances[i])
				.filter(distance -> distance <= distanceThreshold).count() == minReachableCityNum).max().getAsInt();
	}
}
