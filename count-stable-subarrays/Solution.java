import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public long[] countStableSubarrays(int[] nums, int[][] queries) {
    List<Range> ranges = new ArrayList<>();
    int min = -1;
    for (int i = 0; i <= nums.length; ++i) {
      if (i == 0 || i == nums.length || nums[i] < nums[i - 1]) {
        if (min != -1) {
          ranges.add(new Range(min, i - 1));
        }

        min = i;
      }
    }

    long[] prefixSums = new long[ranges.size() + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] =
          prefixSums[i - 1]
              + computeSubarrayNum(ranges.get(i - 1).max() - ranges.get(i - 1).min() + 1);
    }

    return Arrays.stream(queries)
        .mapToLong(
            query -> {
              int leftIndex = find(ranges, query[0]);
              int rightIndex = find(ranges, query[1]);

              if (leftIndex == rightIndex) {
                return computeSubarrayNum(query[1] - query[0] + 1);
              }

              return computeSubarrayNum(ranges.get(leftIndex).max() - query[0] + 1)
                  + computeSubarrayNum(query[1] - ranges.get(rightIndex).min() + 1)
                  + ((leftIndex + 1 <= rightIndex - 1)
                      ? (prefixSums[rightIndex] - prefixSums[leftIndex + 1])
                      : 0);
            })
        .toArray();
  }

  int find(List<Range> ranges, int index) {
    int lower = 0;
    int upper = ranges.size() - 1;
    while (true) {
      int middle = (lower + upper) / 2;
      Range range = ranges.get(middle);
      if (range.min() <= index && range.max() >= index) {
        return middle;
      }

      if (range.max() < index) {
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }
  }

  long computeSubarrayNum(int length) {
    return length * (length + 1L) / 2;
  }
}

record Range(int min, int max) {}
