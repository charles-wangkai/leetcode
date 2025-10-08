import java.util.stream.IntStream;

class Solution {
  static final int ITERATION_NUM = 100;

  public double minmaxGasDist(int[] stations, int k) {
    double lower = 0;
    double upper =
        IntStream.range(0, stations.length - 1)
            .map(i -> stations[i + 1] - stations[i])
            .max()
            .getAsInt();
    for (int i = 0; i < ITERATION_NUM; ++i) {
      double middle = (lower + upper) / 2;
      if (check(stations, k, middle)) {
        upper = middle;
      } else {
        lower = middle;
      }
    }

    return (lower + upper) / 2;
  }

  boolean check(int[] stations, int k, double distance) {
    return IntStream.range(0, stations.length - 1)
            .map(i -> Math.max(0, (int) Math.ceil((stations[i + 1] - stations[i]) / distance) - 1))
            .sum()
        <= k;
  }
}
