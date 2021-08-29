import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    int[] sortedJobIndices =
        IntStream.range(0, startTime.length)
            .boxed()
            .sorted(Comparator.comparing(i -> endTime[i]))
            .mapToInt(x -> x)
            .toArray();

    int[] maxProfits = new int[sortedJobIndices.length + 1];
    for (int i = 1; i < maxProfits.length; ++i) {
      maxProfits[i] =
          Math.max(
              maxProfits[i - 1],
              profit[sortedJobIndices[i - 1]]
                  + maxProfits[findIndex(startTime, endTime, sortedJobIndices, i - 1) + 1]);
    }

    return maxProfits[maxProfits.length - 1];
  }

  int findIndex(int[] startTime, int[] endTime, int[] sortedJobIndices, int lastIndex) {
    int result = -1;
    int lowerIndex = 0;
    int upperIndex = lastIndex - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;

      if (endTime[sortedJobIndices[middleIndex]] <= startTime[sortedJobIndices[lastIndex]]) {
        result = middleIndex;

        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    return result;
  }
}
