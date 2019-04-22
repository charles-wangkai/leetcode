import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int twoCitySchedCost(int[][] costs) {
		Arrays.sort(costs, (cost1, cost2) -> Integer.compare(cost1[0] - cost1[1], cost2[0] - cost2[1]));

		return IntStream.range(0, costs.length).map(i -> (i < costs.length / 2) ? costs[i][0] : costs[i][1]).sum();
	}
}
