import java.util.stream.IntStream;

class Solution {
  public int earliestFinishTime(
      int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
    return Math.min(
        computeFinishTime(landStartTime, landDuration, waterStartTime, waterDuration),
        computeFinishTime(waterStartTime, waterDuration, landStartTime, landDuration));
  }

  int computeFinishTime(int[] startTime1, int[] duration1, int[] startTime2, int[] duration2) {
    int finishTime1 =
        IntStream.range(0, startTime1.length)
            .map(i -> startTime1[i] + duration1[i])
            .min()
            .getAsInt();

    return IntStream.range(0, startTime2.length)
        .map(i -> Math.max(finishTime1, startTime2[i]) + duration2[i])
        .min()
        .getAsInt();
  }
}