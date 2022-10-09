import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int hardestWorker(int n, int[][] logs) {
    return logs[
        IntStream.range(0, logs.length)
            .boxed()
            .min(
                Comparator.comparing((Integer i) -> logs[i][1] - ((i == 0) ? 0 : logs[i - 1][1]))
                    .reversed()
                    .thenComparing(i -> logs[i][0]))
            .get()][
        0];
  }
}
