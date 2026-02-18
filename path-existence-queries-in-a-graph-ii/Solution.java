// https://leetcode.com/problems/path-existence-queries-in-a-graph-ii/solutions/6690608/binary-jumping/

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.comparing(i -> nums[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    Map<Integer, Integer> indexToSortedOrder =
        IntStream.range(0, sortedIndices.length)
            .boxed()
            .collect(Collectors.toMap(i -> sortedIndices[i], i -> i));

    int[][] parents = new int[n][computeExponent(n) + 2];
    int maxIndex = 0;
    for (int i = 0; i < n; ++i) {
      while (maxIndex != n - 1
          && nums[sortedIndices[maxIndex + 1]] - nums[sortedIndices[i]] <= maxDiff) {
        ++maxIndex;
      }

      parents[i][0] = maxIndex;
    }
    for (int exponent = 1; exponent < parents[0].length; ++exponent) {
      for (int i = 0; i < n; ++i) {
        parents[i][exponent] = parents[parents[i][exponent - 1]][exponent - 1];
      }
    }

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                computeDistance(
                    parents,
                    Math.min(indexToSortedOrder.get(query[0]), indexToSortedOrder.get(query[1])),
                    Math.max(indexToSortedOrder.get(query[0]), indexToSortedOrder.get(query[1]))))
        .toArray();
  }

  int computeDistance(int[][] parents, int fromIndex, int toIndex) {
    if (fromIndex == toIndex) {
      return 0;
    }
    if (parents[fromIndex][0] >= toIndex) {
      return 1;
    }

    if (parents[fromIndex][parents[0].length - 1] < toIndex) {
      return -1;
    }

    int exponent = 0;
    while (parents[fromIndex][exponent + 1] < toIndex) {
      ++exponent;
    }

    int subResult = computeDistance(parents, parents[fromIndex][exponent], toIndex);
    if (subResult == -1) {
      return -1;
    }

    return (1 << exponent) + subResult;
  }

  int computeExponent(int x) {
    return 31 - Integer.numberOfLeadingZeros(x);
  }
}
