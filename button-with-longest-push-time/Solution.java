import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int buttonWithLongestTime(int[][] events) {
    return events[
        IntStream.range(0, events.length)
            .boxed()
            .min(
                Comparator.<Integer, Integer>comparing(
                        i -> events[i][1] - ((i == 0) ? 0 : events[i - 1][1]))
                    .reversed()
                    .thenComparing(i -> events[i][0]))
            .get()][
        0];
  }
}