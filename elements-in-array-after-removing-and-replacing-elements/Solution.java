import java.util.Arrays;

class Solution {
  public int[] elementInNums(int[] nums, int[][] queries) {
    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int time = query[0] % (2 * nums.length);

              if (time < nums.length) {
                return (query[1] >= nums.length - time) ? -1 : nums[time + query[1]];
              } else {
                return (query[1] >= time - nums.length) ? -1 : nums[query[1]];
              }
            })
        .toArray();
  }
}