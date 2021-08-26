// https://leetcode.com/problems/find-array-given-subset-sums/discuss/1418799/Python-Short-solution-(update)-explained

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int[] recoverArray(int n, int[] sums) {
    if (n == 1) {
      if (sums[0] == 0) {
        return new int[] {sums[1]};
      } else if (sums[1] == 0) {
        return new int[] {sums[0]};
      }

      return null;
    }

    Arrays.sort(sums);

    for (int element : new int[] {sums[1] - sums[0], sums[0] - sums[1]}) {
      int[] nextSums = buildNextSums(sums, element);
      if (nextSums != null) {
        int[] nextElements = recoverArray(n - 1, nextSums);
        if (nextElements != null) {
          int[] result = Arrays.copyOf(nextElements, nextElements.length + 1);
          result[result.length - 1] = element;

          return result;
        }
      }
    }

    return null;
  }

  int[] buildNextSums(int[] sums, int element) {
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int sum : sums) {
      updateMap(valueToCount, sum, 1);
    }
    if (!valueToCount.containsKey(element)) {
      return null;
    }

    int[] result = new int[sums.length / 2];
    for (int i = 0; i < result.length; ++i) {
      int minValue = valueToCount.firstKey();
      updateMap(valueToCount, minValue, -1);

      if (element >= 0) {
        if (!valueToCount.containsKey(minValue + element)) {
          return null;
        }

        result[i] = minValue;
        updateMap(valueToCount, minValue + element, -1);
      } else {
        if (!valueToCount.containsKey(minValue - element)) {
          return null;
        }

        result[i] = minValue - element;
        updateMap(valueToCount, minValue - element, -1);
      }
    }

    return result;
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
