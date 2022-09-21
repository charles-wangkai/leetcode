import java.util.Arrays;

class Solution {
  public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
    int[] result = new int[queries.length];
    int evenSum = Arrays.stream(nums).filter(x -> x % 2 == 0).sum();
    for (int i = 0; i < result.length; ++i) {
      int value = queries[i][0];
      int index = queries[i][1];

      if (nums[index] % 2 == 0) {
        evenSum -= nums[index];
      }
      nums[index] += value;
      if (nums[index] % 2 == 0) {
        evenSum += nums[index];
      }

      result[i] = evenSum;
    }

    return result;
  }
}
