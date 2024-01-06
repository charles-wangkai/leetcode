import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    int[] sortedJobIndices =
        IntStream.range(0, startTime.length)
            .boxed()
            .sorted(Comparator.comparing(i -> endTime[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] dp = new int[sortedJobIndices.length + 1];
    for (int i = 1; i < dp.length; ++i) {
      dp[i] =
          Math.max(
              dp[i - 1],
              profit[sortedJobIndices[i - 1]]
                  + dp[findIndex(startTime, endTime, sortedJobIndices, i - 1) + 1]);
    }

    return dp[dp.length - 1];
  }

  int findIndex(int[] startTime, int[] endTime, int[] sortedJobIndices, int lastIndex) {
    int result = -1;
    int lower = 0;
    int upper = lastIndex - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (endTime[sortedJobIndices[middle]] <= startTime[sortedJobIndices[lastIndex]]) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
