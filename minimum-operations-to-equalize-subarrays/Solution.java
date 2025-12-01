// https://leetcode.com/problems/minimum-operations-to-equalize-subarrays/solutions/7382847/mos-algorithm-sliding-median-two-multise-0bex/
// https://cp-algorithms.com/data_structures/sqrt_decomposition.html#mos-algorithm

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public long[] minOperations(int[] nums, int k, int[][] queries) {
    int[] lastMatchIndices = new int[nums.length];
    for (int i = lastMatchIndices.length - 1; i >= 0; --i) {
      lastMatchIndices[i] =
          (i == lastMatchIndices.length - 1 || nums[i] % k != nums[i + 1] % k)
              ? i
              : lastMatchIndices[i + 1];
    }

    long[] result = new long[queries.length];
    Arrays.fill(result, -1);

    int blockSize = (int) Math.ceil(Math.sqrt(nums.length));

    int[] validIndices =
        IntStream.range(0, queries.length)
            .filter(i -> lastMatchIndices[queries[i][0]] >= queries[i][1])
            .boxed()
            .sorted(
                Comparator.<Integer, Integer>comparing(i -> queries[i][0] / blockSize)
                    .thenComparing(i -> queries[i][1]))
            .mapToInt(Integer::intValue)
            .toArray();

    State state = new State();
    int leftIndex = 0;
    int rightIndex = -1;
    for (int index : validIndices) {
      int l = queries[index][0];
      int r = queries[index][1];

      while (leftIndex > l) {
        --leftIndex;
        state.addValue(nums[leftIndex]);
      }
      while (rightIndex < r) {
        ++rightIndex;
        state.addValue(nums[rightIndex]);
      }

      while (leftIndex < l) {
        state.removeValue(nums[leftIndex]);
        ++leftIndex;
      }
      while (rightIndex > r) {
        state.removeValue(nums[rightIndex]);
        --rightIndex;
      }

      result[index] =
          (((long) state.getMedian() * state.lowerSize - state.lowerValueSum)
                  + (state.upperValueSum - (long) state.getMedian() * state.upperSize))
              / k;
    }

    return result;
  }
}

class State {
  SortedMap<Integer, Integer> lowerValueToCount = new TreeMap<>();
  int lowerSize = 0;
  long lowerValueSum = 0;

  SortedMap<Integer, Integer> upperValueToCount = new TreeMap<>();
  int upperSize = 0;
  long upperValueSum = 0;

  void addValue(int value) {
    if (lowerSize == 0 || value <= lowerValueToCount.lastKey()) {
      updateMap(lowerValueToCount, value, 1);
      ++lowerSize;
      lowerValueSum += value;
    } else {
      updateMap(upperValueToCount, value, 1);
      ++upperSize;
      upperValueSum += value;
    }

    balance();
  }

  void removeValue(int value) {
    if (lowerValueToCount.containsKey(value)) {
      updateMap(lowerValueToCount, value, -1);
      --lowerSize;
      lowerValueSum -= value;
    } else {
      updateMap(upperValueToCount, value, -1);
      --upperSize;
      upperValueSum -= value;
    }

    balance();
  }

  int getMedian() {
    return lowerValueToCount.lastKey();
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }

  void balance() {
    if (lowerSize == upperSize + 2) {
      int value = lowerValueToCount.lastKey();

      updateMap(lowerValueToCount, value, -1);
      --lowerSize;
      lowerValueSum -= value;

      updateMap(upperValueToCount, value, 1);
      ++upperSize;
      upperValueSum += value;
    } else if (lowerSize + 1 == upperSize) {
      int value = upperValueToCount.firstKey();

      updateMap(upperValueToCount, value, -1);
      --upperSize;
      upperValueSum -= value;

      updateMap(lowerValueToCount, value, 1);
      ++lowerSize;
      lowerValueSum += value;
    }
  }
}
