import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] gcdValues(int[] nums, long[] queries) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int maxValue = Arrays.stream(nums).max().getAsInt();
    long[] gcdNums = new long[maxValue + 1];
    for (int i = 1; i < gcdNums.length; ++i) {
      int multipleNum = 0;
      for (int j = i; j <= maxValue; j += i) {
        multipleNum += valueToCount.getOrDefault(j, 0);
      }

      gcdNums[i] = multipleNum * (multipleNum - 1L) / 2;
    }
    for (int i = gcdNums.length - 1; i >= 1; --i) {
      for (int j = i + i; j < gcdNums.length; j += i) {
        gcdNums[i] -= gcdNums[j];
      }
    }

    long[] prefixSums = new long[gcdNums.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + gcdNums[i];
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int result = -1;
              int lower = 1;
              int upper = maxValue;
              while (lower <= upper) {
                int middle = (lower + upper) / 2;
                if (prefixSums[middle] >= query + 1) {
                  result = middle;
                  upper = middle - 1;
                } else {
                  lower = middle + 1;
                }
              }

              return result;
            })
        .toArray();
  }
}