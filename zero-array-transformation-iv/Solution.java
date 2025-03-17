import java.util.stream.IntStream;

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

  boolean check(int[] nums, int[][] queries, int queryNum) {
    return IntStream.range(0, nums.length).allMatch(i -> isPossible(nums, queries, queryNum, i));
  }

  boolean isPossible(int[] nums, int[][] queries, int queryNum, int index) {
    boolean[] dp = new boolean[nums[index] + 1];
    dp[0] = true;
    for (int i = 0; i < queryNum; ++i) {
      if (queries[i][0] <= index && queries[i][1] >= index) {
        for (int j = dp.length - 1; j >= queries[i][2]; --j) {
          if (dp[j - queries[i][2]]) {
            dp[j] = true;
          }
        }
      }
    }

    return dp[nums[index]];
  }
}