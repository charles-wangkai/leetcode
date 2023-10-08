import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
    int[] beginTimes =
        processorTime.stream()
            .flatMapToInt(t -> IntStream.range(0, 4).map(i -> t))
            .sorted()
            .toArray();
    int[] executionTimes =
        tasks.stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();

    return IntStream.range(0, beginTimes.length)
        .map(i -> beginTimes[i] + executionTimes[i])
        .max()
        .getAsInt();
  }
}
