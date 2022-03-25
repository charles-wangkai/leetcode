import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
  public int twoCitySchedCost(int[][] costs) {
    Arrays.sort(costs, Comparator.comparing(cost -> cost[0] - cost[1]));

    return IntStream.range(0, costs.length)
        .map(i -> (i < costs.length / 2) ? costs[i][0] : costs[i][1])
        .sum();
  }
}
