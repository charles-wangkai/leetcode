class Solution {
  public boolean[] isArraySpecial(int[] nums, int[][] queries) {
    int[] groups = new int[nums.length];
    for (int i = 1; i < groups.length; ++i) {
      groups[i] = groups[i - 1] + ((nums[i] % 2 == nums[i - 1] % 2) ? 1 : 0);
    }

    boolean[] result = new boolean[queries.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = groups[queries[i][0]] == groups[queries[i][1]];
    }

    return result;
  }
}