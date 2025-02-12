import java.util.stream.IntStream;

class Solution {
  public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
    int[] gaps =
        IntStream.concat(
                IntStream.of(startTime[0]),
                IntStream.concat(
                    IntStream.range(0, startTime.length - 1)
                        .map(i -> startTime[i + 1] - endTime[i]),
                    IntStream.of(eventTime - endTime[endTime.length - 1])))
            .toArray();

    int result = -1;
    int sum = IntStream.range(0, k).map(i -> gaps[i]).sum();
    for (int i = k; i < gaps.length; ++i) {
      sum += gaps[i];
      result = Math.max(result, sum);
      sum -= gaps[i - k];
    }

    return result;
  }
}