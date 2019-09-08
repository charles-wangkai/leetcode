import java.util.Arrays;

public class Solution {
	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		int sum = 0;
		while (start != destination) {
			sum += distance[start];

			start = (start + 1) % distance.length;
		}

		return Math.min(sum, Arrays.stream(distance).sum() - sum);
	}
}
