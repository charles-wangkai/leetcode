class Solution {
  public int[] colorTheArray(int n, int[][] queries) {
    int[] result = new int[queries.length];
    int[] nums = new int[n];
    int adjCount = 0;
    for (int i = 0; i < result.length; ++i) {
      if (isSameColor(nums, queries[i][0] - 1)) {
        --adjCount;
      }
      if (isSameColor(nums, queries[i][0])) {
        --adjCount;
      }

      nums[queries[i][0]] = queries[i][1];

      if (isSameColor(nums, queries[i][0] - 1)) {
        ++adjCount;
      }
      if (isSameColor(nums, queries[i][0])) {
        ++adjCount;
      }

      result[i] = adjCount;
    }

    return result;
  }

  boolean isSameColor(int[] nums, int index) {
    return index >= 0
        && index + 1 < nums.length
        && nums[index] != 0
        && nums[index] == nums[index + 1];
  }
}
