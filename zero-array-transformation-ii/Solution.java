class Solution {
  public int minZeroArray(int[] nums, int[][] queries) {
    int result = -1;
    int lower = 0;
    int upper = queries.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, queries, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int[][] queries, int k) {
    int[] deltas = new int[nums.length + 1];
    for (int i = 0; i < k; ++i) {
      deltas[queries[i][0]] += queries[i][2];
      deltas[queries[i][1] + 1] -= queries[i][2];
    }

    int upper = 0;
    for (int i = 0; i < nums.length; ++i) {
      upper += deltas[i];
      if (nums[i] > upper) {
        return false;
      }
    }

    return true;
  }
}