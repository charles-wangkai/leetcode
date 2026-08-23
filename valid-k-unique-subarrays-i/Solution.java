// https://cp-algorithms.com/data_structures/sqrt_decomposition.html#mos-algorithm

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
    int blockSize = (int) Math.ceil(Math.sqrt(nums.length));

    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(
                Comparator.<Integer, Integer>comparing(i -> queries[i][0] / blockSize)
                    .thenComparing(i -> queries[i][1]))
            .mapToInt(Integer::intValue)
            .toArray();

    boolean[] result = new boolean[queries.length];
    Map<Integer, Integer> valueToCount = new HashMap<>();
    int oddCountNum = 0;
    int leftIndex = 0;
    int rightIndex = -1;
    for (int queryIndex : sortedQueryIndices) {
      int l = queries[queryIndex][0];
      int r = queries[queryIndex][1];

      while (leftIndex > l) {
        --leftIndex;

        oddCountNum += (valueToCount.getOrDefault(nums[leftIndex], 0) % 2 == 0) ? 1 : -1;
        updateMap(valueToCount, nums[leftIndex], 1);
      }
      while (rightIndex < r) {
        ++rightIndex;

        oddCountNum += (valueToCount.getOrDefault(nums[rightIndex], 0) % 2 == 0) ? 1 : -1;
        updateMap(valueToCount, nums[rightIndex], 1);
      }

      while (leftIndex < l) {
        oddCountNum += (valueToCount.getOrDefault(nums[leftIndex], 0) % 2 == 0) ? 1 : -1;
        updateMap(valueToCount, nums[leftIndex], -1);

        ++leftIndex;
      }
      while (rightIndex > r) {
        oddCountNum += (valueToCount.getOrDefault(nums[rightIndex], 0) % 2 == 0) ? 1 : -1;
        updateMap(valueToCount, nums[rightIndex], -1);

        --rightIndex;
      }

      result[queryIndex] = valueToCount.size() == k && oddCountNum == 0;
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}