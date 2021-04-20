import java.util.Arrays;

class Solution {
  public int[] solve(int[] nums, int[][] queries) {
    int B = (int) Math.round(Math.floor(Math.sqrt(nums.length)));

    long[][] suffixSums = new long[nums.length][B];
    for (int d = 1; d < B; ++d) {
      for (int i = nums.length - 1; i >= 0; --i) {
        suffixSums[i][d] = ((i + d < nums.length) ? suffixSums[i + d][d] : 0) + nums[i];
      }
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              long result;
              if (query[1] < B) {
                result = suffixSums[query[0]][query[1]];
              } else {
                result = 0;
                for (int i = query[0]; i < nums.length; i += query[1]) {
                  result += nums[i];
                }
              }

              return (int) (result % 1_000_000_007);
            })
        .toArray();
  }
}
