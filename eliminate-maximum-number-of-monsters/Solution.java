import java.util.stream.IntStream;

class Solution {
  public int eliminateMaximum(int[] dist, int[] speed) {
    int[] times =
        IntStream.range(0, dist.length)
            .map(i -> (dist[i] - 1) / speed[i])
            .boxed()
            .sorted()
            .mapToInt(x -> x)
            .toArray();

    int result = 0;
    for (int i = 0; i < times.length && i <= times[i]; ++i) {
      ++result;
    }

    return result;
  }
}
