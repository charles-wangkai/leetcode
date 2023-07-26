import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 10_000_000;

  public int minSpeedOnTime(int[] dist, double hour) {
    int result = -1;
    int lower = 1;
    int upper = LIMIT;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(dist, hour, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] dist, double hour, int speed) {
    return IntStream.range(0, dist.length - 1)
                .map(i -> (dist[i] + speed - 1) / speed)
                .asLongStream()
                .sum()
            + (double) dist[dist.length - 1] / speed
        <= hour;
  }
}
