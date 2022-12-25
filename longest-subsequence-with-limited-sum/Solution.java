import java.util.Arrays;

class Solution {
  public int[] answerQueries(int[] nums, int[] queries) {
    Arrays.sort(nums);

    return Arrays.stream(queries)
        .map(
            query -> {
              int result = 0;
              int sum = 0;
              while (result != nums.length && sum + nums[result] <= query) {
                sum += nums[result];
                ++result;
              }

              return result;
            })
        .toArray();
  }
}
