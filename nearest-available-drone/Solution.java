import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int nearestDrone(int[][] drones, int[] target) {
    int[] distances =
        Arrays.stream(drones)
            .mapToInt(drone -> Math.abs(drone[0] - target[0]) + Math.abs(drone[1] - target[1]))
            .toArray();

    return IntStream.range(0, drones.length)
        .filter(i -> distances[i] <= drones[i][2])
        .boxed()
        .min(Comparator.<Integer, Integer>comparing(i -> distances[i]).thenComparing(i -> i))
        .orElse(-1);
  }
}