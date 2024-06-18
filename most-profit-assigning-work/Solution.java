import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    int[] sortedIndices =
        IntStream.range(0, difficulty.length)
            .boxed()
            .sorted(Comparator.comparing(i -> difficulty[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] maxProfits = new int[sortedIndices.length];
    for (int i = 0; i < maxProfits.length; ++i) {
      maxProfits[i] = Math.max((i == 0) ? -1 : maxProfits[i - 1], profit[sortedIndices[i]]);
    }

    return Arrays.stream(worker)
        .map(limit -> findMaxProfit(difficulty, sortedIndices, maxProfits, limit))
        .sum();
  }

  int findMaxProfit(int[] difficulty, int[] sortedIndices, int[] maxProfits, int limit) {
    int result = 0;
    int lower = 0;
    int upper = sortedIndices.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (difficulty[sortedIndices[middle]] <= limit) {
        result = maxProfits[middle];
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
