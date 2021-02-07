import java.util.Arrays;

class Solution {
  public int maxAbsoluteSum(int[] nums) {
    return Math.max(computeMaxSum(nums), computeMaxSum(Arrays.stream(nums).map(x -> -x).toArray()));
  }

  int computeMaxSum(int[] a) {
    int result = 0;
    int sum = 0;
    for (int ai : a) {
      sum = Math.max(0, sum + ai);
      result = Math.max(result, sum);
    }

    return result;
  }
}
