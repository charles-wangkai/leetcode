import java.util.Arrays;

class Solution {
  public int[] kthRemainingInteger(int[] nums, int[][] queries) {
    int[] maxEvens = new int[nums.length + 1];
    for (int i = 1; i < maxEvens.length; ++i) {
      maxEvens[i] = (nums[i - 1] % 2 == 0) ? nums[i - 1] : maxEvens[i - 1];
    }

    int[] evenCounts = new int[nums.length + 1];
    for (int i = 1; i < evenCounts.length; ++i) {
      evenCounts[i] = evenCounts[i - 1] + ((nums[i - 1] % 2 == 0) ? 1 : 0);
    }

    return Arrays.stream(queries)
        .mapToInt(query -> findKth(maxEvens, evenCounts, query[0], query[1], query[2]))
        .toArray();
  }

  int findKth(int[] maxEvens, int[] evenCounts, int l, int r, int k) {
    int result = -1;
    int lower = 1;
    int upper = k + (maxEvens.length - 1);
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(maxEvens, evenCounts, l, r, k, middle)) {
        result = middle * 2;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] maxEvens, int[] evenCounts, int l, int r, int k, int limit) {
    int index = l - 1;
    int lower = l;
    int upper = r;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (maxEvens[middle + 1] <= limit * 2) {
        index = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return limit - (evenCounts[index + 1] - evenCounts[l]) >= k;
  }
}