import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int maxCapacity(int[] costs, int[] capacity, int budget) {
    int[] sortedIndices =
        IntStream.range(0, costs.length)
            .boxed()
            .sorted(Comparator.comparing(i -> costs[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] prefixMaxCapacities = new int[sortedIndices.length];
    for (int i = 0; i < prefixMaxCapacities.length; ++i) {
      prefixMaxCapacities[i] =
          Math.max(
              (i == 0) ? Integer.MIN_VALUE : prefixMaxCapacities[i - 1],
              capacity[sortedIndices[i]]);
    }

    int result =
        IntStream.range(0, costs.length)
            .filter(i -> costs[i] < budget)
            .map(i -> capacity[i])
            .max()
            .orElse(0);

    int leftIndex = -1;
    for (int rightIndex = sortedIndices.length - 1; rightIndex >= 0; --rightIndex) {
      if (leftIndex == rightIndex) {
        --leftIndex;
      }
      while (leftIndex + 1 < rightIndex
          && costs[sortedIndices[leftIndex + 1]] + costs[sortedIndices[rightIndex]] < budget) {
        ++leftIndex;
      }

      if (leftIndex != -1) {
        result =
            Math.max(result, prefixMaxCapacities[leftIndex] + capacity[sortedIndices[rightIndex]]);
      }
    }

    return result;
  }
}