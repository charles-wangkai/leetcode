import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int LIMIT = 100;

  public int[] minDifference(int[] nums, int[][] queries) {
    @SuppressWarnings("unchecked")
    List<Integer>[] indexLists = new List[LIMIT + 1];
    for (int i = 1; i < indexLists.length; ++i) {
      indexLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < nums.length; ++i) {
      indexLists[nums[i]].add(i);
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int minDiff = Integer.MAX_VALUE;
              int prev = -1;
              for (int i = 1; i < indexLists.length; ++i) {
                if (hasValue(indexLists[i], query[0], query[1])) {
                  if (prev != -1) {
                    minDiff = Math.min(minDiff, i - prev);
                  }

                  prev = i;
                }
              }

              return (minDiff == Integer.MAX_VALUE) ? -1 : minDiff;
            })
        .toArray();
  }

  boolean hasValue(List<Integer> indexList, int l, int r) {
    return findMinIndex(indexList, l) <= findMaxIndex(indexList, r);
  }

  int findMinIndex(List<Integer> indexList, int l) {
    int result = indexList.size();
    int lower = 0;
    int upper = indexList.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (indexList.get(middle) >= l) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  int findMaxIndex(List<Integer> indexList, int r) {
    int result = -1;
    int lower = 0;
    int upper = indexList.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (indexList.get(middle) <= r) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
